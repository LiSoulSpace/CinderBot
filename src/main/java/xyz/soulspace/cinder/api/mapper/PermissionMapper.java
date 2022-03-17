package xyz.soulspace.cinder.api.mapper;

import xyz.soulspace.cinder.api.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 后台用户权限表 Mapper 接口
 * </p>
 *
 * @author soulspace
 * @since 2022-03-02
 */
@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {

}
