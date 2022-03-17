package xyz.soulspace.cinder.api.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import xyz.soulspace.cinder.dto.AdminParam;
import xyz.soulspace.cinder.dto.UpdateAdminPasswordParam;
import xyz.soulspace.cinder.api.entity.Admin;
import com.baomidou.mybatisplus.extension.service.IService;
import xyz.soulspace.cinder.api.entity.Resource;
import xyz.soulspace.cinder.api.entity.Role;

import java.util.List;

/**
 * <p>
 * 后台用户表 服务类
 * </p>
 *
 * @author soulspace
 * @since 2022-03-02
 */
public interface AdminService extends IService<Admin> {
    /**
     * 根据用户名获取后台管理员
     */
    Admin getAdminByUsername(String username);

    /**
     * 注册功能
     */
    Admin register(AdminParam adminParam);

    /**
     * 登录功能
     * @param username 用户名
     * @param password 密码
     * @return 生成的JWT的token
     */
    String login(String username,String password);

    /**
     * 刷新token的功能
     * @param oldToken 旧的token
     */
    String refreshToken(String oldToken);

    /**
     * 根据用户名或昵称分页查询用户
     */
    List<Admin> list(String keyword, Integer pageSize, Integer pageNum);

    /**
     * 修改用户角色关系
     */
    @Transactional
    int updateRole(Long adminId, List<Long> roleIds);

    /**
     * 获取用户对应角色
     */
    List<Role> getRoleList(Long adminId);

    /**
     * 获取指定用户的可访问资源
     */
    List<Resource> getResourceList(Long adminId);

    /**
     * 修改密码
     */
    int updatePassword(UpdateAdminPasswordParam updatePasswordParam);

    /**
     * 获取用户信息
     */
    UserDetails loadUserByUsername(String username);

}
