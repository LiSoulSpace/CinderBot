package xyz.soulspace.cinder.generator.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDate;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.tags.Tag;
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
@TableName("t_holiday")
@Tag(name = "holiday", description = "")
public class Holiday extends Model<Holiday> {

    @TableId(value = "id", type = IdType.AUTO)
    
    private Long id;

    @TableField("holiday_name")
    private String holidayName;

    @TableField("holiday_date")
    private LocalDate holidayDate;

    @TableField("info")
    private String info;

    @TableField("is_lunar")
    private Integer isLunar;

    @TableField(value = "create_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime createTime;

    @TableField(value = "update_time", fill = FieldFill.DEFAULT)
    private LocalDateTime updateTime;


    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
