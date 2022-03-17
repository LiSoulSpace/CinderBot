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
 * 后台用户权限表
 * </p>
 *
 * @author soulspace
 * @since 2022-03-02
 */
@Getter
@Setter
@TableName("ums_permission")
public class Permission extends Model<Permission> {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 父级权限id
     */
    @TableField("pid")
    private Long pid;

    /**
     * 名称
     */
    @TableField("name")
    private String name;

    /**
     * 权限值
     */
    @TableField("value")
    private String value;

    /**
     * 图标
     */
    @TableField("icon")
    private String icon;

    /**
     * 权限类型：0->目录；1->菜单；2->按钮（接口绑定权限）
     */
    @TableField("type")
    private Integer type;

    /**
     * 前端资源路径
     */
    @TableField("uri")
    private String uri;

    /**
     * 启用状态；0->禁用；1->启用
     */
    @TableField("status")
    private Integer status;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 排序
     */
    @TableField("sort")
    private Integer sort;


    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
