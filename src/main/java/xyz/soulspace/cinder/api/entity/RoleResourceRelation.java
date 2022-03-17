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
 * 后台角色资源关系表
 * </p>
 *
 * @author soulspace
 * @since 2022-03-03
 */
@Getter
@Setter
@TableName("ums_role_resource_relation")
public class RoleResourceRelation extends Model<RoleResourceRelation> {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 角色ID
     */
    @TableField("role_id")
    private Long roleId;

    /**
     * 资源ID
     */
    @TableField("resource_id")
    private Long resourceId;

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
