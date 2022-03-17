package xyz.soulspace.cinder.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.soulspace.cinder.api.entity.Admin;
import xyz.soulspace.cinder.api.entity.Blog;
import xyz.soulspace.cinder.api.mapper.BlogMapper;
import xyz.soulspace.cinder.api.service.BlogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import xyz.soulspace.cinder.dto.BlogSummary;

import java.util.List;

/**
 * <p>
 * 博客体 服务实现类
 * </p>
 *
 * @author soulspace
 * @since 2022-03-10
 */
@Service
public class BlogServiceImp extends ServiceImpl<BlogMapper, Blog> implements BlogService {

    @Autowired
    BlogMapper blogMapper;

    @Override
    public List<Blog> list(String keyword, Integer pageSize, Integer pageNum) {
        IPage<Blog> blogIPage = new Page<>(pageNum, pageSize);
        QueryWrapper<Blog> blogQueryWrapper = new QueryWrapper<>();
        blogQueryWrapper.like("title", keyword);
        IPage<Blog> blogIPage1 = blogMapper.selectPage(blogIPage, blogQueryWrapper);
        return blogIPage1.getRecords();
    }

    @Override
    public boolean isExistByTitle(String title) {
        List<Blog> blogList = blogMapper.selectByTitle(title);
        return !blogList.isEmpty();
    }

    @Override
    public List<Blog> getByTitle(String title) {
        return blogMapper.selectByTitle(title);
    }

    @Override
    public List<BlogSummary> listSummary(String keyword, Integer pageSize, Integer pageNum) {
        List<BlogSummary> blogSummaries =
                blogMapper.selectBlogSummaryByPage(keyword, (pageNum - 1) * pageSize, pageSize);
        return blogSummaries;
    }
}
