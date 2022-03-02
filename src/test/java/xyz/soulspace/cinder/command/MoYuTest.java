package xyz.soulspace.cinder.command;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.soulspace.cinder.service.MoYuService;

@SpringBootTest
public class MoYuTest {
    public static final Logger LOGGER = LoggerFactory.getLogger(MoYuTest.class);
    @Autowired
    MoYuService moYuService;

    @Test
    void testMoYuString(){
        String moYuString = moYuService.moYuString();
        LOGGER.info(moYuString);
    }
}
