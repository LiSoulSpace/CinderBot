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
 * 后台资源表
 * </p>
 *
 * @author soulspace
 * @since 2022-03-02
 */
@Getter
@Setter
@TableName("ums_resource")
public class Resource extends Model<Resource> {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 资源名称
     */
    @TableField("name")
    private String name;

    /**
     * 资源URL
     */
    @TableField("url")
    private String url;

    /**
     * 描述
     */
    @TableField("description")
    private String description;

    /**
     * 资源分类ID
     */
    @TableField("category_id")
    private Long categoryId;


    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
