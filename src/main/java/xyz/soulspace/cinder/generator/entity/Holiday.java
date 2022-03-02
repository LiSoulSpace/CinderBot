package xyz.soulspace.cinder.generator.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDate;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.*;

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
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName("t_holiday")
@Schema(name = "节日", title = "Holiday")
public class Holiday extends Model<Holiday> {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("holiday_name")
    @Schema(title = "节日名字")
    private String holidayName;

    @TableField("holiday_date")
    @Schema(title = "节日日期")
    private LocalDate holidayDate;

    @TableField("info")
    @Schema(title = "节日信息")
    private String info;

    @TableField("is_lunar")
    @Schema(title = "是否为阴历")
    private Integer isLunar;

    @TableField(value = "create_time", fill = FieldFill.INSERT_UPDATE)
    @Schema(title = "创建时间")
    private LocalDateTime createTime;

    @TableField(value = "update_time", fill = FieldFill.DEFAULT)
    @Schema(title = "更新时间")
    private LocalDateTime updateTime;


    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
