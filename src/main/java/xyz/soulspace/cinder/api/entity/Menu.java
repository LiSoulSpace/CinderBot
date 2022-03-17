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
 * 后台菜单表
 * </p>
 *
 * @author soulspace
 * @since 2022-03-02
 */
@Getter
@Setter
@TableName("ums_menu")
public class Menu extends Model<Menu> {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 父级ID
     */
    @TableField("parent_id")
    private Long parentId;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 菜单名称
     */
    @TableField("title")
    private String title;

    /**
     * 菜单级数
     */
    @TableField("level")
    private Integer level;

    /**
     * 菜单排序
     */
    @TableField("sort")
    private Integer sort;

    /**
     * 前端名称
     */
    @TableField("name")
    private String name;

    /**
     * 前端图标
     */
    @TableField("icon")
    private String icon;

    /**
     * 前端隐藏
     */
    @TableField("hidden")
    private Integer hidden;


    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
