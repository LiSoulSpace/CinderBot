package xyz.soulspace.cinder.api.service.impl;

import xyz.soulspace.cinder.api.entity.GrowthChangeHistory;
import xyz.soulspace.cinder.api.mapper.GrowthChangeHistoryMapper;
import xyz.soulspace.cinder.api.service.GrowthChangeHistoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 成长值变化历史记录表 服务实现类
 * </p>
 *
 * @author soulspace
 * @since 2022-03-02
 */
@Service
public class GrowthChangeHistoryServiceImp extends ServiceImpl<GrowthChangeHistoryMapper, GrowthChangeHistory> implements GrowthChangeHistoryService {

}
