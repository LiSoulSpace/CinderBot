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
 * 后台用户角色表
 * </p>
 *
 * @author soulspace
 * @since 2022-03-02
 */
@Getter
@Setter
@TableName("ums_role")
public class Role extends Model<Role> {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 名称
     */
    @TableField("name")
    private String name;

    /**
     * 描述
     */
    @TableField("description")
    private String description;

    /**
     * 后台用户数量
     */
    @TableField("admin_count")
    private Integer adminCount;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 启用状态：0->禁用；1->启用
     */
    @TableField("status")
    private Integer status;

    @TableField("sort")
    private Integer sort;


    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
