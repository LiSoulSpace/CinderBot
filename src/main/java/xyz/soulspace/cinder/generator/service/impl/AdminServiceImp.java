package xyz.soulspace.cinder.generator.service.impl;

import xyz.soulspace.cinder.generator.entity.Admin;
import xyz.soulspace.cinder.generator.mapper.AdminMapper;
import xyz.soulspace.cinder.generator.service.AdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 后台用户表 服务实现类
 * </p>
 *
 * @author soulspace
 * @since 2022-03-02
 */
@Service
public class AdminServiceImp extends ServiceImpl<AdminMapper, Admin> implements AdminService {

}
