package xyz.soulspace.cinder.api.mapper;

import xyz.soulspace.cinder.api.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author soulspace
 * @since 2022-02-22
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
