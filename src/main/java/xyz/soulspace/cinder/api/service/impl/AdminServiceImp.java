package xyz.soulspace.cinder.api.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import xyz.soulspace.cinder.component.security.JwtTokenUtil;
import xyz.soulspace.cinder.dto.AdminParam;
import xyz.soulspace.cinder.dto.AdminUserDetails;
import xyz.soulspace.cinder.dto.UpdateAdminPasswordParam;
import xyz.soulspace.cinder.exception.ApiException;
import xyz.soulspace.cinder.api.entity.*;
import xyz.soulspace.cinder.api.mapper.AdminLoginLogMapper;
import xyz.soulspace.cinder.api.mapper.AdminMapper;
import xyz.soulspace.cinder.api.mapper.AdminRoleRelationMapper;
import xyz.soulspace.cinder.api.service.AdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import xyz.soulspace.cinder.service.AdminCacheService;
import xyz.soulspace.cinder.utils.RequestUtil;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 后台用户表 服务实现类
 * </p>
 *
 * @author soulspace
 * @since 2022-03-02
 */
@Service
public class AdminServiceImp extends ServiceImpl<AdminMapper, Admin> implements AdminService {
    public static final Logger LOGGER = LoggerFactory.getLogger(AdminServiceImp.class);
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private AdminRoleRelationMapper adminRoleRelationMapper;
    @Autowired
    private AdminLoginLogMapper loginLogMapper;
    @Autowired
    private AdminCacheService adminCacheService;

    @Override
    public Admin getAdminByUsername(String username) {
        List<Admin> adminList = adminMapper.selectByUsername(username);
        if (adminList != null && adminList.size() > 0) {
            Admin admin = adminList.get(0);
            adminCacheService.setAdmin(admin);
            return admin;
        }
        return null;
    }

    @Override
    public Admin register(AdminParam umsAdminParam) {
        Admin admin = new Admin();
        //查询是否有相同用户名的用户
        List<Admin> umsAdminList = adminMapper.selectByUsername(umsAdminParam.getUsername());
        if (umsAdminList.size() > 0) {
            return null;
        }
        //将密码进行加密操作
        String encodePassword = passwordEncoder.encode(umsAdminParam.getPassword());
        admin.setPassword(encodePassword);
        admin.setEmail(umsAdminParam.getEmail());
        admin.setNickName(umsAdminParam.getNickName());
        admin.setIcon(umsAdminParam.getIcon());
        admin.setUsername(umsAdminParam.getUsername());
        int insert = adminMapper.insert(admin);
        if (insert==1) return admin;
        else return null;
    }

    @Override
    public String login(String username, String password) {
        String token = null;
        //密码需要客户端加密后传递
        try {
            UserDetails userDetails = loadUserByUsername(username);
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new ApiException("密码不正确");
            }
            if (!userDetails.isEnabled()) {
                throw new ApiException("帐号已被禁用");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
//            updateLoginTimeByUsername(username);
            insertLoginLog(username);
        } catch (AuthenticationException e) {
            LOGGER.warn("登录异常:{}", e.getMessage());
        }
        return token;
    }

    /**
     * 添加登录记录
     *
     * @param username 用户名
     */
    private void insertLoginLog(String username) {
        Admin admin = getAdminByUsername(username);
        if (admin == null) return;
        AdminLoginLog loginLog = new AdminLoginLog();
        loginLog.setAdminId(admin.getId());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        loginLog.setIp(RequestUtil.getRequestIp(request));
        loginLogMapper.insert(loginLog);
    }

    /**
     * 根据用户名修改登录时间
     */
    private void updateLoginTimeByUsername(String username) {
        adminMapper.updateLoginTimeByUsername(LocalDateTime.now(), username);
    }

    @Override
    public String refreshToken(String oldToken) {
        return jwtTokenUtil.refreshHeadToken(oldToken);
    }

    @Override
    public List<Admin> list(String keyword, Integer pageSize, Integer pageNum) {
        IPage<Admin> adminIPage = new Page<>(pageNum, pageSize);
        QueryWrapper<Admin> wrapper = new QueryWrapper<>();
        wrapper.like("username", keyword);
        IPage<Admin> adminIPage1 = adminMapper.selectPage(adminIPage, wrapper);
        return adminIPage1.getRecords();
    }

    @Override
    public int updateRole(Long adminId, List<Long> roleIds) {
        int count = roleIds == null ? 0 : roleIds.size();
        //先删除原来的关系
        adminRoleRelationMapper.deleteById(adminId);
        //建立新关系
        if (!CollectionUtils.isEmpty(roleIds)) {
            List<AdminRoleRelation> list = new ArrayList<>();
            for (Long roleId : roleIds) {
                AdminRoleRelation roleRelation = new AdminRoleRelation();
                roleRelation.setAdminId(adminId);
                roleRelation.setRoleId(roleId);
                list.add(roleRelation);
            }
            adminRoleRelationMapper.insertList(list);
        }
        adminCacheService.delResourceList(adminId);
        return count;
    }

    @Override
    public List<Role> getRoleList(Long adminId) {
        return adminRoleRelationMapper.getRoleList(adminId);
    }

    @Override
    public List<Resource> getResourceList(Long adminId) {
        List<Resource> resourceList = adminCacheService.getResourceList(adminId);
        if (CollUtil.isNotEmpty(resourceList)) {
            return resourceList;
        }
        resourceList = adminRoleRelationMapper.getResourceList(adminId);
        if (CollUtil.isNotEmpty(resourceList)) {
            adminCacheService.setResourceList(adminId, resourceList);
        }
        return resourceList;
    }

    @Override
    public int updatePassword(UpdateAdminPasswordParam param) {
        if (StrUtil.isEmpty(param.getUsername())
                || StrUtil.isEmpty(param.getOldPassword())
                || StrUtil.isEmpty(param.getNewPassword())) {
            return -1;
        }
        List<Admin> adminList = adminMapper.selectByUsername(param.getUsername());
        if (CollUtil.isEmpty(adminList)) {
            return -2;
        }
        Admin admin = adminList.get(0);
        if (!passwordEncoder.matches(param.getOldPassword(), admin.getPassword())) {
            return -3;
        }
        admin.setPassword(passwordEncoder.encode(param.getNewPassword()));
        adminMapper.updateById(admin);
        adminCacheService.delAdmin(admin.getId());
        return 1;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        //获取用户信息
        Admin admin = getAdminByUsername(username);
        if (admin != null) {
            List<Resource> resourceList = getResourceList(admin.getId());
            return new AdminUserDetails(admin, resourceList);
        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }
}
