package xyz.soulspace.cinder.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import xyz.soulspace.cinder.api.entity.Admin;
import xyz.soulspace.cinder.api.entity.Blog;
import xyz.soulspace.cinder.api.mapper.AdminMapper;
import xyz.soulspace.cinder.api.service.BlogService;
import xyz.soulspace.cinder.dto.AdminParam;
import xyz.soulspace.cinder.dto.BlogSummary;
import xyz.soulspace.cinder.dto.BlogUploadParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 博客体 前端控制器
 * </p>
 *
 * @author soulspace
 * @since 2022-03-10
 */
@Controller
@CrossOrigin
@RequestMapping("/api/blog")
@Tag(name = "博客控制器(BlogController)")
public class BlogController {
    public static final Logger LOGGER = LoggerFactory.getLogger(BlogController.class);
    @Autowired
    BlogService blogService;
    @Autowired
    AdminMapper adminMapper;

    @Operation(summary = "提交博客")
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> uploadBlog(@RequestBody BlogUploadParam blogUploadParam) {
        if (blogService.isExistByTitle(blogUploadParam.getTitle()))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        Blog blog = new Blog();
        blog.setHtmlEntity(blogUploadParam.getHtmlEntity());
        blog.setTitle(blogUploadParam.getTitle());
        blog.setMarkdownEntity(blogUploadParam.getMarkdownEntity());
        blog.setSummary(blogUploadParam.getSummary());
        LOGGER.info(blogUploadParam.getUsername());
        LOGGER.info(adminMapper.selectByUsername(blogUploadParam.getUsername()).get(0).getId().toString());
        blog.setUserId(adminMapper.selectByUsername(blogUploadParam.getUsername()).get(0).getId());
        boolean save = blogService.save(blog);
        if (save) return ResponseEntity.ok("提交成功");
        else return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @Operation(summary = "通过标题获取博客")
    @RequestMapping(value = "getBlogById", method = RequestMethod.GET)
    public ResponseEntity<?> getByTitle(@RequestParam(value = "id") Integer id){
        Blog blogServiceById = blogService.getById(id);
        return ResponseEntity.ok(blogServiceById);
    }

    @Operation(summary = "分页获取博客列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> list(@RequestParam(value = "keyword", required = false) String keyword,
                                  @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                  @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<Blog> blogList = blogService.list(keyword, pageSize, pageNum);
        return new ResponseEntity<>(blogList, HttpStatus.OK);
    }

    @Operation(summary = "分页获取博客总结列表")
    @RequestMapping(value = "/listSummary", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> listSummary(@RequestParam(value = "keyword", required = false) String keyword,
                                         @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                         @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum){
        List<BlogSummary> blogSummaries = blogService.listSummary(keyword, pageSize, pageNum);
        if (blogSummaries!=null)return ResponseEntity.ok(blogSummaries);
        else return ResponseEntity.notFound().build();
    }


    @Operation(summary = "删除博客")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> deleteBlog(@RequestBody int blogId) {
        boolean removeById = blogService.removeById(blogId);
        if (removeById) return ResponseEntity.ok("删除成功");
        else return ResponseEntity.internalServerError().build();
    }

    @Operation(summary = "更新博客")
    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<?> updateBlog(@RequestBody Blog blogTemp) {
        Blog blogById = blogService.getById(blogTemp.getId());
        if (blogById == null) return ResponseEntity.badRequest().build();
        boolean updateById = blogService.updateById(blogTemp);
        if (updateById)return ResponseEntity.ok("更新成功");
        else return ResponseEntity.internalServerError().build();
    }

    @Operation(summary = "获取博客数量")
    @ResponseBody
    @RequestMapping(value = "/countAll", method = RequestMethod.GET)
    public ResponseEntity<?> countBlog(){
        long count = blogService.count();
        return ResponseEntity.ok(count);
    }
}
