package xyz.soulspace.cinder.service.sys;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class BlackListService {
    private static final Logger logger = LoggerFactory.getLogger(BlackListService.class);

    @Value("${file.path.config}")
    private String configPath;

    private String getFilePath(){
        return configPath + File.separator + "black_list";
    }


}
