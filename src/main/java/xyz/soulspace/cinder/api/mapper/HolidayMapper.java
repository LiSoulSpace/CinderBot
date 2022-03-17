package xyz.soulspace.cinder.api.mapper;
import java.time.LocalDate;
import org.apache.ibatis.annotations.Param;

import xyz.soulspace.cinder.api.entity.Holiday;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author soulspace
 * @since 2022-02-22
 */
@Mapper
public interface HolidayMapper extends BaseMapper<Holiday> {
    List<Holiday> selectHolidayDateAndIsLunarByHolidayName(@Param("holidayName") String holidayName);

    int updateHolidayDateByHolidayName(@Param("holidayDate") LocalDate holidayDate, @Param("holidayName") String holidayName);

    List<Holiday> selectHolidayDateByHolidayName(@Param("holidayName") String holidayName);
}
