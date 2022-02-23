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
public class HolidayServiceImp extends ServiceImpl<HolidayMapper, Holiday> implements HolidayService {
    public static final Logger LOGGER = LoggerFactory.getLogger(HolidayServiceImp.class);

    @Autowired
    HolidayMapper holidayMapper;

    @Override
    public int getIntervalToHolidayByName(String holidayName) {
        LocalDateTime startLocalDateTime = LocalDateTime.now();
        LocalDateTime endLocalDateTime = getLocalDateTimeByName(holidayName);
        List<Holiday> holidayList = holidayMapper.selectHolidayDateAndIsLunarByHolidayName(holidayName);
        try {
            Duration between = LocalDateTimeUtil.between(startLocalDateTime, endLocalDateTime);
            long days = between.toDays() + 1;
            return (int) days;
        } catch (Exception e) {
            LOGGER.warn(e.getMessage());
        }
        return -1;
    }

    @Override
    public boolean checkIsOutdatedByName(String holidayName) {
        LocalDateTime startLocalDateTime = LocalDateTime.now();
        LocalDateTime endLocalDateTime = getLocalDateTimeByName(holidayName);
        try {
            Duration between = LocalDateTimeUtil.between(startLocalDateTime, endLocalDateTime);
            long days = between.toDays();
            if (days < 0) return true;
        } catch (Exception e) {
            LOGGER.warn(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean updateNextDateByName(String holidayName) {
        if (checkIsOutdatedByName(holidayName)) {
            List<Holiday> holidays = holidayMapper.selectHolidayDateByHolidayName(holidayName);
            LocalDate holidayDate = holidays.get(0).getHolidayDate();
            LocalDate newHolidayDate = holidayDate;
            if(holidayName.equals("周末"))
                newHolidayDate = holidayDate.plusYears(1);
            else newHolidayDate = holidayDate.plusDays(7);
            int i = holidayMapper.updateHolidayDateByHolidayName(newHolidayDate, holidayName);
            if (i > 0) return true;
            else return false;
        } else return false;
    }

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
            LocalDateTime endLocalDate = holiday.getHolidayDate().atStartOfDay();
            return endLocalDate;
        }
    }
}