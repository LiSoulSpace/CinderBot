package xyz.soulspace.cinder.api.service.impl;

import xyz.soulspace.cinder.api.entity.AdminPermissionRelation;
import xyz.soulspace.cinder.api.mapper.AdminPermissionRelationMapper;
import xyz.soulspace.cinder.api.service.AdminPermissionRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 后台用户和权限关系表(除角色中定义的权限以外的加减权限) 服务实现类
 * </p>
 *
 * @author soulspace
 * @since 2022-03-02
 */
@Service
public class AdminPermissionRelationServiceImp extends ServiceImpl<AdminPermissionRelationMapper, AdminPermissionRelation> implements AdminPermissionRelationService {

}
