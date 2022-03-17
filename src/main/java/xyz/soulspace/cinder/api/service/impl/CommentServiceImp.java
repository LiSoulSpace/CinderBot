package xyz.soulspace.cinder.api.service.impl;

import xyz.soulspace.cinder.api.entity.Comment;
import xyz.soulspace.cinder.api.mapper.CommentMapper;
import xyz.soulspace.cinder.api.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author soulspace
 * @since 2022-03-10
 */
@Service
public class CommentServiceImp extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}
