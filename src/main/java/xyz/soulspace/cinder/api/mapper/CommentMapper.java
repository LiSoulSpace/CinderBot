package xyz.soulspace.cinder.api.mapper;

import xyz.soulspace.cinder.api.entity.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author soulspace
 * @since 2022-03-10
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

}
