package xyz.soulspace.cinder.api.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import xyz.soulspace.cinder.api.entity.AdminRoleRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import xyz.soulspace.cinder.api.entity.Resource;
import xyz.soulspace.cinder.api.entity.Role;

/**
 * <p>
 * 后台用户和角色关系表 Mapper 接口
 * </p>
 *
 * @author soulspace
 * @since 2022-03-02
 */
@Mapper
public interface AdminRoleRelationMapper extends BaseMapper<AdminRoleRelation> {
    List<AdminRoleRelation> selectByRoleId(@Param("roleId") Long roleId);
    List<AdminRoleRelation> selectByRoleIds(@Param("roleIds") List<Long> roleIds);

    /**
     * 批量插入用户角色关系
     */
    int insertList(@Param("list") List<AdminRoleRelation> adminRoleRelationList);

    List<Role> getRoleList(Long adminId);

    /**
     * 获取用户所有可访问资源
     */
    List<Resource> getResourceList(@Param("adminId") Long adminId);

    /**
     * 获取资源相关用户ID列表
     */
    List<Long> getAdminIdList(@Param("resourceId") Long resourceId);
}
