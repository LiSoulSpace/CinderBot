package xyz.soulspace.cinder.api.entity;

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
 * 积分消费设置
 * </p>
 *
 * @author soulspace
 * @since 2022-03-02
 */
@Getter
@Setter
@TableName("ums_integration_consume_setting")
public class IntegrationConsumeSetting extends Model<IntegrationConsumeSetting> {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 每一元需要抵扣的积分数量
     */
    @TableField("deduction_per_amount")
    private Integer deductionPerAmount;

    /**
     * 每笔订单最高抵用百分比
     */
    @TableField("max_percent_per_order")
    private Integer maxPercentPerOrder;

    /**
     * 每次使用积分最小单位100
     */
    @TableField("use_unit")
    private Integer useUnit;

    /**
     * 是否可以和优惠券同用；0->不可以；1->可以
     */
    @TableField("coupon_status")
    private Integer couponStatus;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
