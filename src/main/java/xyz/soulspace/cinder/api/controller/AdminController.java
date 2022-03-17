package xyz.soulspace.cinder.api.controller;

import cn.hutool.core.collection.CollUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import xyz.soulspace.cinder.dto.AdminLoginParam;
import xyz.soulspace.cinder.dto.AdminParam;
import xyz.soulspace.cinder.dto.UpdateAdminPasswordParam;
import xyz.soulspace.cinder.api.entity.Admin;
import xyz.soulspace.cinder.api.entity.Role;
import xyz.soulspace.cinder.api.service.AdminService;
import xyz.soulspace.cinder.api.service.RoleService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 后台用户表 前端控制器
 * </p>
 *
 * @author soulspace
 * @since 2022-03-02
 */
@Controller
@RequestMapping("/api/admin")
@CrossOrigin
@Tag(name = "管理员控制器(AdminController)")
public class AdminController {
    public static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Autowired
    private AdminService adminService;
    @Autowired
    private RoleService roleService;

    @Operation(summary = "用户注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Admin> register(@Validated @RequestBody AdminParam AdminParam,
                                          HttpServletResponse httpServletResponse) {
        Admin Admin = adminService.register(AdminParam);
        if (Admin == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        String token = adminService.login(Admin.getUsername(), Admin.getPassword());
        httpServletResponse.addCookie(new Cookie("username", Admin.getUsername()));
        httpServletResponse.addCookie(new Cookie("password", Admin.getPassword()));
        httpServletResponse.addCookie(new Cookie("token", token));
        httpServletResponse.addCookie(new Cookie("tokenHead", tokenHead));
        return new ResponseEntity<>(Admin, HttpStatus.OK);
    }

    @Operation(summary = "登录以后返回token")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> login(@Validated @RequestBody AdminLoginParam AdminLoginParam,
                                   HttpServletResponse httpServletResponse) {
        String token = adminService.login(AdminLoginParam.getUsername(), AdminLoginParam.getPassword());
        LOGGER.info("token:" + token);
        if (token == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        httpServletResponse.addCookie(new Cookie("username", AdminLoginParam.getUsername()));
        httpServletResponse.addCookie(new Cookie("password", AdminLoginParam.getPassword()));
        httpServletResponse.addCookie(new Cookie("token", token));
        httpServletResponse.addCookie(new Cookie("tokenHead", tokenHead));
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return new ResponseEntity<>(tokenMap, HttpStatus.OK);

    }

    @Operation(summary = "刷新token")
    @RequestMapping(value = "/refreshToken", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, String>> refreshToken(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String refreshToken = adminService.refreshToken(token);
        if (refreshToken == null) {
            return new ResponseEntity<>(new HashMap<String, String>(), HttpStatus.UNAUTHORIZED);
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", refreshToken);
        tokenMap.put("tokenHead", tokenHead);
        return new ResponseEntity<>(tokenMap, HttpStatus.OK);
    }

    @Operation(summary = "获取当前登录用户信息")
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getAdminInfo(Principal principal) {
        if (principal == null) {
            return new ResponseEntity<String>("No principal", HttpStatus.UNAUTHORIZED);
        }
        LOGGER.warn(principal.getName());
        String username = principal.getName();
        Admin Admin = adminService.getAdminByUsername(username);
        Map<String, Object> data = new HashMap<>();
        data.put("username", Admin.getUsername());
        data.put("menus", roleService.getMenuList(Admin.getId()));
        data.put("icon", Admin.getIcon());
        List<Role> roleList = adminService.getRoleList(Admin.getId());
        for (Role role : roleList) {
            LOGGER.warn(role.toString());
        }
        if (CollUtil.isNotEmpty(roleList)) {
            List<String> roles = roleList.stream().map(Role::getName).collect(Collectors.toList());
            data.put("roles", roles);
        }
        return new ResponseEntity<Map<String, Object>>(data, HttpStatus.OK);
    }

    @Operation(summary = "登出功能")
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> logout() {
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @Operation(summary = "根据用户名或姓名分页获取用户列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> list(@RequestParam(value = "keyword", required = false) String keyword,
                                  @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                  @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<Admin> adminList = adminService.list(keyword, pageSize, pageNum);
        return new ResponseEntity<>(adminList, HttpStatus.OK);
    }

    @Operation(summary = "获取指定用户信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Admin> getItem(@PathVariable Long id) {
        Admin admin = adminService.getById(id);
        return new ResponseEntity<>(admin, HttpStatus.OK);
    }

    @Operation(summary = "修改指定用户信息")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Admin admin) {
        admin.setId(id);
        boolean count = adminService.updateById(admin);
        if (count) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<String>("Update failed", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Operation(summary = "修改指定用户密码")
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> updatePassword(@Validated @RequestBody UpdateAdminPasswordParam updatePasswordParam) {
        int status = adminService.updatePassword(updatePasswordParam);
        if (status > 0) {
            return new ResponseEntity<>(status, HttpStatus.OK);
        } else if (status == -1) {
            return new ResponseEntity<>("提交参数不合法", HttpStatus.INTERNAL_SERVER_ERROR);
        } else if (status == -2) {
            return new ResponseEntity<>("找不到该用户", HttpStatus.INTERNAL_SERVER_ERROR);
        } else if (status == -3) {
            return new ResponseEntity<>("旧密码错误", HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<>("Unexpected Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "删除指定用户信息")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> delete(@PathVariable Long id) {
        boolean count = adminService.removeById(id);
        if (count) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Operation(summary = "修改帐号状态")
    @RequestMapping(value = "/updateStatus/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> updateStatus(@PathVariable Long id, @RequestParam(value = "status") Integer status) {
        Admin Admin = adminService.getById(id);
        Admin.setStatus(status);
        boolean count = adminService.updateById(Admin);
        if (count) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Operation(summary = "给用户分配角色")
    @RequestMapping(value = "/role/update", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> updateRole(@RequestParam("adminId") Long adminId,
                                        @RequestParam("roleIds") List<Long> roleIds) {
        int count = adminService.updateRole(adminId, roleIds);
        if (count >= 0) {
            return new ResponseEntity<>(count, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Operation(summary = "获取指定用户的角色")
    @RequestMapping(value = "/role/{adminId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Role>> getRoleList(@PathVariable Long adminId) {
        List<Role> roleList = adminService.getRoleList(adminId);
        return new ResponseEntity<>(roleList, HttpStatus.OK);
    }
}
