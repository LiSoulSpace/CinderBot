package xyz.soulspace.cinder.generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import xyz.soulspace.cinder.generator.entity.User;
import xyz.soulspace.cinder.generator.service.UserService;
import xyz.soulspace.cinder.generator.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author soulspace
* @description 针对表【c_user】的数据库操作Service实现
* @createDate 2022-02-15 19:23:14
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




