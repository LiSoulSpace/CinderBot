package xyz.soulspace.cinder.generator.mapper;

import xyz.soulspace.cinder.generator.entity.Admin;
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

}
