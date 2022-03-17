package xyz.soulspace.cinder.api.service.impl;

import xyz.soulspace.cinder.api.entity.Permission;
import xyz.soulspace.cinder.api.mapper.PermissionMapper;
import xyz.soulspace.cinder.api.service.PermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 后台用户权限表 服务实现类
 * </p>
 *
 * @author soulspace
 * @since 2022-03-02
 */
@Service
public class PermissionServiceImp extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

}
