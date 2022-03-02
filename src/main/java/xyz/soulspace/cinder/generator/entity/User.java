package xyz.soulspace.cinder.generator.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author soulspace
 * @since 2022-02-22
 */
@Getter
@Setter
@TableName("c_user")
@Schema(title = "用户基础信息")
public class User extends Model<User> {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("username")
    @Schema(title = "用户名")
    private String username;

    @TableField("password")
    @Schema(title = "密码")
    private String password;

    @TableField("info")
    private String info;


    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
