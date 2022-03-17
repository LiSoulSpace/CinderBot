package xyz.soulspace.cinder.api.mapper;

import xyz.soulspace.cinder.api.entity.AdminLoginLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 后台用户登录日志表 Mapper 接口
 * </p>
 *
 * @author soulspace
 * @since 2022-03-02
 */
@Mapper
public interface AdminLoginLogMapper extends BaseMapper<AdminLoginLog> {

}
