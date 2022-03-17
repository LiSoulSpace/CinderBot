package xyz.soulspace.cinder.api.mapper;
import java.time.LocalDateTime;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import xyz.soulspace.cinder.api.entity.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 后台用户表 Mapper 接口
 * </p>
 *
 * @author soulspace
 * @since 2022-03-02
 */
@Mapper
public interface AdminMapper extends BaseMapper<Admin> {
    int insertSelective(Admin admin);

    List<Admin> selectByUsername(@Param("username") String username);

    int updateLoginTimeByUsername(@Param("loginTime") LocalDateTime loginTime, @Param("username") String username);
}
