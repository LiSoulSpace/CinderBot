package xyz.soulspace.cinder.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class JobMain {
    public static final Logger logger = LoggerFactory.getLogger(JobMain.class);


}
