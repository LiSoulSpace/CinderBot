package xyz.soulspace.cinder.mbg;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.soulspace.cinder.generator.entity.Holiday;
import xyz.soulspace.cinder.generator.mapper.HolidayMapper;
import xyz.soulspace.cinder.generator.service.HolidayService;

import java.time.LocalDate;
import java.time.Month;

@SpringBootTest
public class HolidayServiceTest {
    public static final Logger LOGGER = LoggerFactory.getLogger(HolidayServiceTest.class);
    @Autowired
    HolidayService holidayService;
    @Autowired
    HolidayMapper holidayMapper;

    @Test
    void testGetIntervalToHolidayByName() {
        int intervalToHolidayByName =
                holidayService.getIntervalToHolidayByName("周末");
        LOGGER.info(String.valueOf(intervalToHolidayByName));
    }

    @Test
    void testCheckIsOutdatedByName() {
        boolean b = holidayService.checkIsOutdatedByName("元宵节");
        LOGGER.info(String.valueOf(b));
    }

    @Test
    void updateHoliday() {
        Holiday holiday = new Holiday();
        holiday.setHolidayName("test2");
        holiday.setHolidayDate(LocalDate.of(2022, Month.NOVEMBER, 11));
        holiday.setIsLunar(0);
        Wrapper<Holiday> wrapper = new UpdateWrapper<Holiday>().eq("holiday_name", "test");
        holidayMapper.update(holiday, wrapper);
    }

    @Test
    void insertHoliday(){
        Holiday holiday = new Holiday();
        holiday.setHolidayName("劳动节");
        holiday.setHolidayDate(LocalDate.of(2023, 5, 1));
        holiday.setIsLunar(0);
        holidayMapper.insert(holiday);
        holiday = new Holiday();
        holiday.setHolidayName("端午节");
        holiday.setHolidayDate(LocalDate.of(2023, 5, 5));
        holiday.setIsLunar(1);
        holidayMapper.insert(holiday);
        holiday = new Holiday();
        holiday.setHolidayName("中秋节");
        holiday.setHolidayDate(LocalDate.of(2023, 8, 15));
        holiday.setIsLunar(1);
        holidayMapper.insert(holiday);
        holiday = new Holiday();
        holiday.setHolidayName("国庆节");
        holiday.setHolidayDate(LocalDate.of(2023, 10, 1));
        holiday.setIsLunar(0);
        holidayMapper.insert(holiday);
    }
}
