package xyz.soulspace.cinder.generator.mapper;

import xyz.soulspace.cinder.generator.entity.AdminPermissionRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 后台用户和权限关系表(除角色中定义的权限以外的加减权限) Mapper 接口
 * </p>
 *
 * @author soulspace
 * @since 2022-03-02
 */
@Mapper
public interface AdminPermissionRelationMapper extends BaseMapper<AdminPermissionRelation> {

}
