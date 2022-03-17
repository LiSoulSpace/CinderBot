package xyz.soulspace.cinder.api.mapper;
import org.apache.ibatis.annotations.Param;

import xyz.soulspace.cinder.api.entity.RoleResourceRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 后台角色资源关系表 Mapper 接口
 * </p>
 *
 * @author soulspace
 * @since 2022-03-03
 */
@Mapper
public interface RoleResourceRelationMapper extends BaseMapper<RoleResourceRelation> {
    int deleteByRoleId(@Param("roleId") Long roleId);
}
