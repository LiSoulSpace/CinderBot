package xyz.soulspace.cinder;

import cn.hutool.core.date.BetweenFormatter;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.soulspace.cinder.api.entity.Holiday;
import xyz.soulspace.cinder.api.mapper.HolidayMapper;
import xyz.soulspace.cinder.api.mapper.UserMapper;
import xyz.soulspace.cinder.api.service.HolidayService;

import java.time.LocalDate;
import java.util.*;

@SpringBootTest
class CinderApplicationTests {
    public static final  Logger LOGGER = LoggerFactory.getLogger(CinderApplicationTests.class);

    @Autowired
    UserMapper userMapper;
    @Autowired
    HolidayService holidayService;
    @Autowired
    HolidayMapper holidayMapper;

    @Test
    void testService(){
        LOGGER.info("-----test service-----");
        List<Holiday> holidayList = holidayService.list();
        LOGGER.info(Arrays.toString(holidayList.toArray()));
        LOGGER.info("-----test service over-----");
    }

    @Test
    public void testSelect() {
        LOGGER.info("----- select method test ------");
        List<Holiday> holidays = holidayMapper.selectList(null);
        LOGGER.info(Arrays.toString(holidays.toArray()));
        List<Holiday> holidayList = holidayMapper.selectHolidayDateAndIsLunarByHolidayName("国庆节");
        LOGGER.info(Arrays.toString(holidayList.toArray()));
        Holiday selectById = holidayMapper.selectById(2L);
        LOGGER.info(selectById.toString());

        LOGGER.info("----- select method test over ------");
    }

    @Test
    void testInsert(){
        LocalDate localDate = LocalDate.of(2022, 11, 11);
        Holiday holiday = new Holiday();
        holiday.setHolidayName("test");
        holiday.setIsLunar(0);
        holiday.setHolidayDate(localDate);
        int insert = holidayMapper.insert(holiday);
        LOGGER.info(holiday.toString());
        LOGGER.info(String.valueOf(insert));
    }

    @Test
    void testDelete(){
        Holiday holiday = holidayMapper.selectById(2L);
        LOGGER.info(holiday.toString());
    }

    @Test
    public void dateTest(){
        String dateStr1 = "2017-03-01 22:33:23";
        Date date1 = DateUtil.parse(dateStr1);
        String dateStr2 = "2017-04-01 23:33:23";
        Date date2 = DateUtil.parse(dateStr2);
        //相差一个月，31天
        long between = DateUtil.between(date1, date2, DateUnit.HOUR);
        //Level.MINUTE表示精确到分
        String formatBetween = DateUtil.formatBetween(between, BetweenFormatter.Level.HOUR);
        //输出：31天1小时
        LOGGER.info(formatBetween);
    }

    @Test
    public void other(){
        String property = System.getProperty("user.dir");
        System.out.println(property);
    }
}
