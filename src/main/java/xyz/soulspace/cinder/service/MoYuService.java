package xyz.soulspace.cinder.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.soulspace.cinder.api.service.HolidayService;

import java.util.*;

@Service
public class MoYuService {
    public static final Logger logger = LoggerFactory.getLogger(MoYuService.class);

    List<String> holidayNames = List.of("考研", "春节", "元宵节", "清明节", "劳动节", "端午节", "中秋节", "国庆节");

    @Autowired
    HolidayService holidayService;

    public String moYuString() {
        List<Integer> hoursOfIntervalToHoliday = new ArrayList<>();
        for (String holiday : holidayNames) {
            int intervalToHolidayByName = holidayService.getIntervalToHolidayByName(holiday);
            hoursOfIntervalToHoliday.add(intervalToHolidayByName);
        }
        Map<String, Double> map = new HashMap<>();
        for (int i = 0; i < holidayNames.size(); i++) {
            map.put(holidayNames.get(i), (double) hoursOfIntervalToHoliday.get(i) / 24.0);
        }

        StringBuilder stringBuffer = new StringBuilder();
        stringBuffer.append("摸鱼小助手提醒您：\n");
        Set<String> stringSet = map.keySet();
        for (String holidayName : stringSet) {
            stringBuffer.append("距离 ").append(holidayName).append(" 还有 ").append(String.format("%.1f", map.get(holidayName))).append(" 天\n");
        }
        stringBuffer.deleteCharAt(stringBuffer.length()-1);
        String s = stringBuffer.toString();
        return s;
    }

}
