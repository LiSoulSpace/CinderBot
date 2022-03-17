package xyz.soulspace.cinder.api.service.impl;

import xyz.soulspace.cinder.api.entity.User;
import xyz.soulspace.cinder.api.mapper.UserMapper;
import xyz.soulspace.cinder.api.service.UserService;
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
