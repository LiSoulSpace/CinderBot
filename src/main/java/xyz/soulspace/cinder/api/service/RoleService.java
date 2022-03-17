package xyz.soulspace.cinder.api.service;

import org.springframework.transaction.annotation.Transactional;
import xyz.soulspace.cinder.api.entity.Menu;
import xyz.soulspace.cinder.api.entity.Resource;
import xyz.soulspace.cinder.api.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 后台用户角色表 服务类
 * </p>
 *
 * @author soulspace
 * @since 2022-03-02
 */
public interface RoleService extends IService<Role> {
    /**
     * 根据管理员ID获取对应菜单
     */
    List<Menu> getMenuList(Long adminId);

    /**
     * 获取角色相关菜单
     */
    List<Menu> listMenu(Long roleId);

    /**
     * 获取角色相关资源
     */
    List<Resource> listResource(Long roleId);

    /**
     * 给角色分配菜单
     */
    @Transactional
    int allocMenu(Long roleId, List<Long> menuIds);

    /**
     * 给角色分配资源
     */
    @Transactional
    int allocResource(Long roleId, List<Long> resourceIds);
}
