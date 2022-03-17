package xyz.soulspace.cinder.api.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author soulspace
 * @since 2022-03-10
 */
@Getter
@Setter
@TableName("bms_comment")
public class Comment extends Model<Comment> {

    /**
     * 自增id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 是否为子评论
     */
    @TableField("is_sub_comment")
    private Integer isSubComment;

    /**
     * 博客id
     */
    @TableField("blog_id")
    private Long blogId;

    /**
     * 用户id
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 评论本体
     */
    @TableField("comment_body")
    private String commentBody;


    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
