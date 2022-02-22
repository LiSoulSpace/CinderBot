package xyz.soulspace.cinder.generator.service.impl;

import xyz.soulspace.cinder.generator.entity.User;
import xyz.soulspace.cinder.generator.mapper.UserMapper;
import xyz.soulspace.cinder.generator.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author soulspace
 * @since 2022-02-22
 */
@Service
public class UserServiceImp extends ServiceImpl<UserMapper, User> implements UserService {

}
