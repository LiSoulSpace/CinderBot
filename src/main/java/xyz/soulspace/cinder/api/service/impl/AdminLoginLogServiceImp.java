package xyz.soulspace.cinder.api.service.impl;

import xyz.soulspace.cinder.api.entity.AdminLoginLog;
import xyz.soulspace.cinder.api.mapper.AdminLoginLogMapper;
import xyz.soulspace.cinder.api.service.AdminLoginLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 后台用户登录日志表 服务实现类
 * </p>
 *
 * @author soulspace
 * @since 2022-03-02
 */
@Service
public class AdminLoginLogServiceImp extends ServiceImpl<AdminLoginLogMapper, AdminLoginLog> implements AdminLoginLogService {

}
