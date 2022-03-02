package xyz.soulspace.cinder.generator.service.impl;

import cn.hutool.core.date.ChineseDate;
import cn.hutool.core.date.DateBetween;
import cn.hutool.core.date.LocalDateTimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.soulspace.cinder.generator.entity.Holiday;
import xyz.soulspace.cinder.generator.mapper.HolidayMapper;
import xyz.soulspace.cinder.generator.service.HolidayService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import xyz.soulspace.cinder.utils.MyDateUtil;

import java.time.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author soulspace
 * @since 2022-02-22
 */
@Service
public class HolidayServiceImp extends ServiceImpl<HolidayMapper, Holiday>
        implements HolidayService {
    public static final Logger LOGGER = LoggerFactory.getLogger(HolidayServiceImp.class);

    final HolidayMapper holidayMapper;

    @Autowired
    public HolidayServiceImp(HolidayMapper holidayMapper) {
        this.holidayMapper = holidayMapper;
    }

    @Override
    public int getIntervalToHolidayByName(String holidayName) {
        LocalDateTime endLocalDateTime = getLocalDateTimeByName(holidayName);
        if (checkIsOutdatedByName(holidayName, endLocalDateTime)) {
            updateNextDateByName(holidayName);
        }
        LocalDateTime startLocalDateTime = LocalDateTime.now();
        try {
            Duration between = LocalDateTimeUtil.between(startLocalDateTime, endLocalDateTime);
            long hours = between.toHours();
            return (int) hours;
        } catch (Exception e) {
            LOGGER.warn(e.getMessage());
        }
        return -1;
    }

    @Override
    public boolean checkIsOutdatedByName(String holidayName, LocalDateTime endLocalDateTime) {
        LocalDateTime startLocalDateTime = LocalDateTime.now();
        try {
            Duration between = LocalDateTimeUtil.between(startLocalDateTime, endLocalDateTime);
            long minutes = between.toMinutes();
            if (minutes <= 0) return true;
        } catch (Exception e) {
            LOGGER.warn(e.getMessage());
        }
        return false;
    }

    /**
     * 更新节日的日期
     * @param holidayName 节日的名字
     * @return 更新是否成功
     */
    @Override
    public boolean updateNextDateByName(String holidayName) {
        List<Holiday> holidays = holidayMapper.selectHolidayDateByHolidayName(holidayName);
        LocalDate holidayDate = holidays.get(0).getHolidayDate();
        LocalDate newHolidayDate = holidayDate;
        if (holidayName.equals("周末"))
            newHolidayDate = holidayDate.plusDays(7);
        else newHolidayDate = holidayDate.plusYears(1);
        int i = holidayMapper.updateHolidayDateByHolidayName(newHolidayDate, holidayName);
        if (i > 0) return true;
        else return false;
    }

    /**
     * 获取节日的 LocalDataTime 通过节日的名字
     * @param holidayName 节日名字
     * @return 节日的日期(当天0时0分)
     */
    @Override
    public LocalDateTime getLocalDateTimeByName(String holidayName) {
        List<Holiday> holidayList = holidayMapper.selectHolidayDateAndIsLunarByHolidayName(holidayName);
        Holiday holiday = holidayList.get(0);
        LOGGER.info(holiday.toString());
        if (holiday.getIsLunar() == 1) {
            ChineseDate chineseDate = new ChineseDate(
                    holiday.getHolidayDate().getYear(),
                    holiday.getHolidayDate().getMonthValue(),
                    holiday.getHolidayDate().getDayOfMonth(),
                    false
            );
            LocalDateTime endLocalDate = LocalDateTime.of(
                    chineseDate.getGregorianYear(),
                    chineseDate.getGregorianMonth() + 1,
                    chineseDate.getGregorianDay(),
                    0, 0, 0
            );
            return endLocalDate;
        } else {
            LocalDateTime endLocalDate = holiday.getHolidayDate().atTime(0, 0);
            return endLocalDate;
        }
    }

    /**
     * 判断今天是不是周末
     * @return 是则返回 true
     */
    @Override
    public boolean isWeekendToday() {
        Calendar instance = Calendar.getInstance();
        int dayOfWeek = instance.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == 1 || dayOfWeek == 7) {
            return true;
        } else return false;
    }
}