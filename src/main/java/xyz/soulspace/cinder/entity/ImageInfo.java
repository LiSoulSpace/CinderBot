package xyz.soulspace.cinder.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImageInfo {
    /**
     * 图片名称（文件名称）
     */
    private String name;
    /**
     * 图片类型（后缀)
     */
    private String type;
    /**
     * 图片大小
     */
    private Long size;
    /**
     * 图片长度
     */
    private Integer width;
    /**
     * 图片高度
     */
    private Integer height;
    /**
     * 本地路径
     */
    private String localPath;
    /**
     * 网络连接
     */
    private String imgUrl;
}
