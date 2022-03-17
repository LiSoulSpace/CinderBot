package xyz.soulspace.cinder.api.service.impl;

import xyz.soulspace.cinder.api.entity.Menu;
import xyz.soulspace.cinder.api.mapper.MenuMapper;
import xyz.soulspace.cinder.api.service.MenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 后台菜单表 服务实现类
 * </p>
 *
 * @author soulspace
 * @since 2022-03-02
 */
@Service
public class MenuServiceImp extends ServiceImpl<MenuMapper, Menu> implements MenuService {

}
