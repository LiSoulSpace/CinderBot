package xyz.soulspace.cinder.api.service;

import xyz.soulspace.cinder.api.entity.Admin;
import xyz.soulspace.cinder.api.entity.Blog;
import com.baomidou.mybatisplus.extension.service.IService;
import xyz.soulspace.cinder.dto.BlogSummary;

import java.util.List;

/**
 * <p>
 * 博客体 服务类
 * </p>
 *
 * @author soulspace
 * @since 2022-03-10
 */
public interface BlogService extends IService<Blog> {
    List<Blog> list(String keyword, Integer pageSize, Integer pageNum);

    boolean isExistByTitle(String title);

    List<Blog> getByTitle(String title);

    List<BlogSummary> listSummary(String keyword, Integer pageSize, Integer pageNum);


}
