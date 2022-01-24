package xyz.soulspace.cinder.service.sys;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfigService {
    public static final Logger logger = LoggerFactory.getLogger(ConfigService.class);

    @Autowired
    private BlackListService blackListService;

}
