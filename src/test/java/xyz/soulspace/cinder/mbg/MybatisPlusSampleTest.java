package xyz.soulspace.cinder.mbg;
import com.baomidou.mybatisplus.test.autoconfigure.MybatisPlusTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import xyz.soulspace.cinder.api.mapper.HolidayMapper;

@MybatisPlusTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MybatisPlusSampleTest {

    @Autowired
    private HolidayMapper holidayMapper;

    @Test
    void testSelect() {

    }
}
