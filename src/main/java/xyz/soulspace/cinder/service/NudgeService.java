package xyz.soulspace.cinder.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class NudgeService {
    private static final Logger logger = LoggerFactory.getLogger(NudgeService.class);

    @Value("${bot.account}")
    private Long botAccount;


}
