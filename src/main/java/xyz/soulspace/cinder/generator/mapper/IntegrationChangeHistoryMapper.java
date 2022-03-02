package xyz.soulspace.cinder.generator.mapper;

import xyz.soulspace.cinder.generator.entity.IntegrationChangeHistory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 积分变化历史记录表 Mapper 接口
 * </p>
 *
 * @author soulspace
 * @since 2022-03-02
 */
@Mapper
public interface IntegrationChangeHistoryMapper extends BaseMapper<IntegrationChangeHistory> {

}
