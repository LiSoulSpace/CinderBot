package xyz.soulspace.cinder.generator.service.impl;

import xyz.soulspace.cinder.generator.entity.Holiday;
import xyz.soulspace.cinder.generator.mapper.HolidayMapper;
import xyz.soulspace.cinder.generator.service.HolidayService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author soulspace
 * @since 2022-02-22
 */
@Service
public class HolidayServiceImp extends ServiceImpl<HolidayMapper, Holiday> implements HolidayService {

}
