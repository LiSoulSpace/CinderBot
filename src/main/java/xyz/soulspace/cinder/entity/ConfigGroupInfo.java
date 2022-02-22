package xyz.soulspace.cinder.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ConfigGroupInfo {
    /**
     * 订阅的微博id列表
     */
    List<Long> weiboPushIds = new ArrayList<>();
    /**
     * 订阅的B站视频动态uid列表
     */
    List<Long> biliPushIds = new ArrayList<>();
    /**
     * 群公告
     */
    private String groupNotice;
}
