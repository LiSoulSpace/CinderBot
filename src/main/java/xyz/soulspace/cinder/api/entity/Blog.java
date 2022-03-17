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
 * 博客体
 * </p>
 *
 * @author soulspace
 * @since 2022-03-10
 */
@Getter
@Setter
@TableName("bms_blog")
public class Blog extends Model<Blog> {

    /**
     * markdown本体
     */
    @TableField("markdown_entity")
    private String markdownEntity;

    /**
     * 自增id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * html本体
     */
    @TableField("html_entity")
    private String htmlEntity;

    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 博客用户
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 博客标题
     */
    @TableField("title")
    private String title;

    /**
     * 博客总结
     */
    @TableField("summary")
    private String summary;


    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
