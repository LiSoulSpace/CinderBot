package xyz.soulspace.cinder.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import xyz.soulspace.cinder.api.entity.Comment;
import xyz.soulspace.cinder.api.mapper.CommentMapper;
import xyz.soulspace.cinder.api.service.CommentService;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author soulspace
 * @since 2022-03-10
 */
@Controller
@CrossOrigin
@RequestMapping("/api/comment")
@Tag(name = "评论控制器(CommentController)")
public class CommentController {
    @Autowired
    CommentService commentService;

    @Operation(summary = "创建评论")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> createComment(@RequestBody Comment comment){
        boolean save = commentService.save(comment);
        if(save)return ResponseEntity.ok(comment);
        else return ResponseEntity.internalServerError().build();
    }

    @Operation(summary = "删除评论")
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> deleteComment(@RequestBody Comment comment){
        boolean removeById = commentService.removeById(comment.getId());
        if (removeById)return ResponseEntity.ok("删除成功");
        else return ResponseEntity.internalServerError().build();
    }

}
