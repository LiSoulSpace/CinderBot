package xyz.soulspace.cinder.api.mapper;

import org.apache.ibatis.annotations.Param;
import xyz.soulspace.cinder.api.entity.Menu;
import xyz.soulspace.cinder.api.entity.Resource;
import xyz.soulspace.cinder.api.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 后台用户角色表 Mapper 接口
 * </p>
 *
 * @author soulspace
 * @since 2022-03-02
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {
    /**
     * 根据后台用户ID获取菜单
     */
    List<Menu> getMenuList(@Param("adminId") Long adminId);
    /**
     * 根据角色ID获取菜单
     */
    List<Menu> getMenuListByRoleId(@Param("roleId") Long roleId);
    /**
     * 根据角色ID获取资源
     */
    List<Resource> getResourceListByRoleId(@Param("roleId") Long roleId);
}
