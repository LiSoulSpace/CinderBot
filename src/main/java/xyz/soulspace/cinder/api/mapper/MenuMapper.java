package xyz.soulspace.cinder.api.mapper;
import org.apache.ibatis.annotations.Param;

import xyz.soulspace.cinder.api.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 后台菜单表 Mapper 接口
 * </p>
 *
 * @author soulspace
 * @since 2022-03-02
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {
    int updateHidden(@Param("hidden") Integer hidden);
}
