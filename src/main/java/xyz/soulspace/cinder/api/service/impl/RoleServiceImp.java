package xyz.soulspace.cinder.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import xyz.soulspace.cinder.api.entity.*;
import xyz.soulspace.cinder.api.mapper.RoleMapper;
import xyz.soulspace.cinder.api.mapper.RoleMenuRelationMapper;
import xyz.soulspace.cinder.api.mapper.RoleResourceRelationMapper;
import xyz.soulspace.cinder.api.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import xyz.soulspace.cinder.service.AdminCacheService;

import java.util.List;

/**
 * <p>
 * 后台用户角色表 服务实现类
 * </p>
 *
 * @author soulspace
 * @since 2022-03-02
 */
@Service
public class RoleServiceImp extends ServiceImpl<RoleMapper, Role> implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RoleMenuRelationMapper roleMenuRelationMapper;
    @Autowired
    private RoleResourceRelationMapper roleResourceRelationMapper;
    @Autowired
    private AdminCacheService adminCacheService;

    @Override
    public List<Menu> getMenuList(Long adminId) {
        return roleMapper.getMenuList(adminId);
    }

    @Override
    public List<Menu> listMenu(Long roleId) {
        return roleMapper.getMenuListByRoleId(roleId);
    }

    @Override
    public List<Resource> listResource(Long roleId) {
        return roleMapper.getResourceListByRoleId(roleId);
    }

    @Override
    public int allocMenu(Long roleId, List<Long> menuIds) {
        //先删除原有关系
        roleMenuRelationMapper.deleteByRoleId(roleId);
        //批量插入新关系
        for (Long menuId : menuIds) {
            RoleMenuRelation relation = new RoleMenuRelation();
            relation.setRoleId(roleId);
            relation.setMenuId(menuId);
            roleMenuRelationMapper.insert(relation);
        }
        return menuIds.size();
    }

    @Override
    public int allocResource(Long roleId, List<Long> resourceIds) {
        //先删除原有关系
        roleResourceRelationMapper.deleteByRoleId(roleId);
        //批量插入新关系
        for (Long resourceId : resourceIds) {
            RoleResourceRelation relation = new RoleResourceRelation();
            relation.setRoleId(roleId);
            relation.setResourceId(resourceId);
            roleResourceRelationMapper.insert(relation);
        }
        adminCacheService.delResourceListByRole(roleId);
        return resourceIds.size();
    }
}
