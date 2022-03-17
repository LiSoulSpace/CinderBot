package xyz.soulspace.cinder.api.mapper;
import org.apache.ibatis.annotations.Param;

import xyz.soulspace.cinder.api.entity.RoleMenuRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 后台角色菜单关系表 Mapper 接口
 * </p>
 *
 * @author soulspace
 * @since 2022-03-04
 */
@Mapper
public interface RoleMenuRelationMapper extends BaseMapper<RoleMenuRelation> {
    int deleteByRoleId(@Param("roleId") Long roleId);
}
