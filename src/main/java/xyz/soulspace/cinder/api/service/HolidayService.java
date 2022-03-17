package xyz.soulspace.cinder.api.service;

import org.apache.ibatis.annotations.Param;
import xyz.soulspace.cinder.api.entity.Holiday;
import com.baomidou.mybatisplus.extension.service.IService;

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
    boolean checkIsOutdatedByName(@Param("holidayName") String holidayName, @Param("endLocalDateTime")LocalDateTime endLocalDateTime);
    boolean updateNextDateByName(@Param("holidayName") String holidayName);
    LocalDateTime getLocalDateTimeByName(@Param("holidayName") String holidayName);
    boolean isWeekendToday();
}
