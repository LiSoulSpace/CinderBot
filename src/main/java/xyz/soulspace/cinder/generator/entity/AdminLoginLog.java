package xyz.soulspace.cinder.generator.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
/**
 * <p>
 * 后台用户登录日志表
 * </p>
 *
 * @author soulspace
 * @since 2022-03-02
 */
@Getter
@Setter
@TableName("ums_admin_login_log")
public class AdminLoginLog extends Model<AdminLoginLog> {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("admin_id")
    private Long adminId;

    @TableField(value = "create_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime createTime;

    @TableField("ip")
    private String ip;

    @TableField("address")
    private String address;

    /**
     * 浏览器登录类型
     */
    @TableField("user_agent")
    private String userAgent;

    @TableField(value = "update_time", fill = FieldFill.DEFAULT)
    private LocalDateTime updateTime;


    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
