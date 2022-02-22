package xyz.soulspace.cinder.utils;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static xyz.soulspace.cinder.utils.LunarCalendar.daysInMonth;
import static xyz.soulspace.cinder.utils.LunarCalendar.lunarToSolar;

public class LunarCalendarTests {
    @Test
    void testCalendar(){
        System.out.println(Arrays.toString(lunarToSolar(2022, 4, 5, false)));
    }
}
