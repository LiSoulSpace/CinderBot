package xyz.soulspace.cinder.generator.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.soulspace.cinder.generator.entity.Holiday;
import xyz.soulspace.cinder.generator.service.HolidayService;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author soulspace
 * @since 2022-02-22
 */
@Controller
@RequestMapping("/generator/holiday")
@CrossOrigin
public class HolidayController {
    private static final Logger LOGGER = LoggerFactory.getLogger(HolidayController.class);

    final HolidayService holidayService;

    public HolidayController(HolidayService holidayService) {
        this.holidayService = holidayService;
    }

    @GetMapping("/api/getHolidays")
    @ResponseBody
    @Tag(name = "获取所有假日信息")
    public List<Holiday> getHolidays(){
        List<Holiday> holidayList = holidayService.list();
        return holidayList;
    }
}
