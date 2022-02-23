package xyz.soulspace.cinder.generator.service;

import org.apache.ibatis.annotations.Param;
import xyz.soulspace.cinder.generator.entity.Holiday;
import com.baomidou.mybatisplus.extension.service.IService;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author soulspace
 * @since 2022-02-22
 */
public interface HolidayService extends IService<Holiday> {
    int getIntervalToHolidayByName(@Param("holidayName") String holidayName);
    boolean checkIsOutdatedByName(@Param("holidayName") String holidayName);
    boolean updateNextDateByName(@Param("holidayName") String holidayName);
    LocalDateTime getLocalDateTimeByName(@Param("holidayName") String holidayName);
}
