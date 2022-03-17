package xyz.soulspace.cinder.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 * 后台角色菜单关系表
 * </p>
 *
 * @author soulspace
 * @since 2022-03-04
 */
@Getter
@Setter
@TableName("ums_role_menu_relation")
public class RoleMenuRelation extends Model<RoleMenuRelation> {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 角色ID
     */
    @TableField("role_id")
    private Long roleId;

    /**
     * 菜单ID
     */
    @TableField("menu_id")
    private Long menuId;


    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
