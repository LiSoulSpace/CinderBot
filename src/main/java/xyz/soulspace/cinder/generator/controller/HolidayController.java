package xyz.soulspace.cinder.generator.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import xyz.soulspace.cinder.generator.entity.Holiday;
import xyz.soulspace.cinder.generator.service.HolidayService;

import java.util.List;

/**
 * <p>
 * Holiday控制器
 * </p>
 *
 * @author soulspace
 * @since 2022-02-22
 */
@Controller
@RequestMapping("/generator/holiday")
@CrossOrigin
@Tag(name = "节日控制器(HolidayController)")
public class HolidayController {
    private static final Logger LOGGER = LoggerFactory.getLogger(HolidayController.class);

    final HolidayService holidayService;

    public HolidayController(HolidayService holidayService) {
        this.holidayService = holidayService;
    }

    @GetMapping("/api/getHolidays")
    @ResponseBody
    @Operation(summary = "获取所有假日信息")
    public List<Holiday> getHolidays() {
        List<Holiday> holidayList = holidayService.list();
        return holidayList;
    }

    @RequestMapping(value = "/api/getDaysToHolidayByName/{holidayName}", method = RequestMethod.GET)
    @ResponseBody
    @Operation(summary = "获取距离某节日的天数")
    public double getDaysToHolidayByName(@PathVariable("holidayName") String holidayName) {
        int intervalToHolidayByName = holidayService.getIntervalToHolidayByName(holidayName);
        double days = (double) intervalToHolidayByName / 24.0;
        return days;
    }

    @RequestMapping(value = "/api/getHoursToHolidayByName/{holidayName}", method = RequestMethod.GET)
    @ResponseBody
    @Operation(summary = "获取距离某节日的小时数")
    public int getHoursToHolidayByName(@PathVariable("holidayName") String holidayName) {
        int intervalToHolidayByName = holidayService.getIntervalToHolidayByName(holidayName);
        return intervalToHolidayByName;
    }
}
