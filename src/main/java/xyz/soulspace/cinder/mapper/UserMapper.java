package xyz.soulspace.cinder.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import xyz.soulspace.cinder.pojo.User;

@Mapper
@Repository
public interface UserMapper {
    User findByUsername(String username);

    User getByUsernameAndPassword(String username, String password);

    void addUser(User user);
}
