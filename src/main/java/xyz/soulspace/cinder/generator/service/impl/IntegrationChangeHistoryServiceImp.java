package xyz.soulspace.cinder.generator.service.impl;

import xyz.soulspace.cinder.generator.entity.IntegrationChangeHistory;
import xyz.soulspace.cinder.generator.mapper.IntegrationChangeHistoryMapper;
import xyz.soulspace.cinder.generator.service.IntegrationChangeHistoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 积分变化历史记录表 服务实现类
 * </p>
 *
 * @author soulspace
 * @since 2022-03-02
 */
@Service
public class IntegrationChangeHistoryServiceImp extends ServiceImpl<IntegrationChangeHistoryMapper, IntegrationChangeHistory> implements IntegrationChangeHistoryService {

}
