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
 * 成长值变化历史记录表
 * </p>
 *
 * @author soulspace
 * @since 2022-03-02
 */
@Getter
@Setter
@TableName("ums_growth_change_history")
public class GrowthChangeHistory extends Model<GrowthChangeHistory> {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("member_id")
    private Long memberId;

    @TableField(value = "create_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime createTime;

    /**
     * 改变类型：0->增加；1->减少
     */
    @TableField("change_type")
    private Integer changeType;

    /**
     * 积分改变数量
     */
    @TableField("change_count")
    private Integer changeCount;

    /**
     * 操作人员
     */
    @TableField("operate_man")
    private String operateMan;

    /**
     * 操作备注
     */
    @TableField("operate_note")
    private String operateNote;

    /**
     * 积分来源：0->购物；1->管理员修改
     */
    @TableField("source_type")
    private Integer sourceType;

    @TableField(value = "update_time", fill = FieldFill.DEFAULT)
    private LocalDateTime updateTime;


    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
