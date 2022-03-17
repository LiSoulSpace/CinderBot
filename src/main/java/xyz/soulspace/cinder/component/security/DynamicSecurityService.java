package xyz.soulspace.cinder.component.security;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Map;


public interface DynamicSecurityService {
    /**
     * 加载资源ANT通配符和资源对应MAP
     */
    Map<String, ConfigAttribute> loadDataSource();
}
