package xyz.soulspace.cinder.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.soulspace.cinder.api.entity.Resource;
import xyz.soulspace.cinder.api.mapper.ResourceMapper;
import xyz.soulspace.cinder.api.service.ResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 后台资源表 服务实现类
 * </p>
 *
 * @author soulspace
 * @since 2022-03-02
 */
@Service
public class ResourceServiceImp extends ServiceImpl<ResourceMapper, Resource> implements ResourceService {
    @Autowired
    ResourceMapper resourceMapper;

    @Override
    public List<Resource> list(Long categoryId, String nameKeyword, String urlKeyword, Integer pageSize, Integer pageNum) {
        IPage<Resource> resourceIPage = new Page<>(pageNum, pageSize);
        QueryWrapper<Resource> wrapper = new QueryWrapper<>();
        wrapper.like("name", nameKeyword);
        wrapper.like("url", urlKeyword);
        IPage<Resource> resourceIPage1 = resourceMapper.selectPage(resourceIPage, wrapper);
        return resourceIPage1.getRecords();
    }
}
