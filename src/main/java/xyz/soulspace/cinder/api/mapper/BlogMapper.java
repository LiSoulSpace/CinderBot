package xyz.soulspace.cinder.api.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import xyz.soulspace.cinder.api.entity.Blog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import xyz.soulspace.cinder.dto.BlogSummary;

/**
 * <p>
 * 博客体 Mapper 接口
 * </p>
 *
 * @author soulspace
 * @since 2022-03-10
 */
@Mapper
public interface BlogMapper extends BaseMapper<Blog> {
    List<Blog> selectMarkdownEntityByUserId(@Param("userId") Long userId);

    List<Blog> selectHtmlEntityByUserId(@Param("userId") Long userId);

    List<Blog> selectByTitle(@Param("title") String title);

    List<BlogSummary> selectBlogSummaryByPage(@Param("keyword") String keyword,
                                              @Param("offset") Integer offset,
                                              @Param("pageNum") Integer pageNum);
}
