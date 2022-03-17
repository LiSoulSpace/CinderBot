package xyz.soulspace.cinder.api.service;

import org.apache.ibatis.annotations.Param;
import xyz.soulspace.cinder.api.entity.Resource;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 后台资源表 服务类
 * </p>
 *
 * @author soulspace
 * @since 2022-03-02
 */
public interface ResourceService extends IService<Resource> {
    /**
     * 分页查询资源
     */
    List<Resource> list(@Param("categoryId") Long categoryId,
                        @Param("nameKeyword") String nameKeyword,
                        @Param("urlKeyword") String urlKeyword,
                        @Param("pageSize") Integer pageSize,
                        @Param("pageNum") Integer pageNum);
}
