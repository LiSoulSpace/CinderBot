package xyz.soulspace.cinder.mbg;
import com.baomidou.mybatisplus.test.autoconfigure.MybatisPlusTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import xyz.soulspace.cinder.generator.entity.Holiday;
import xyz.soulspace.cinder.generator.mapper.HolidayMapper;

import java.util.Arrays;
import java.util.List;

@MybatisPlusTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MybatisPlusSampleTest {

    @Autowired
    private HolidayMapper holidayMapper;

    @Test
    void testSelect() {

    }
}
