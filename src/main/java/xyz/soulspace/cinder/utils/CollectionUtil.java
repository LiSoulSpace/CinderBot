package xyz.soulspace.cinder.utils;

import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class CollectionUtil {

    /**
     * 集合是否为空
     * 期望 非空
     *
     * @param list 集合
     * @return 是否为空
     */
    public static boolean isNotEmpty(List<?> list) {
        return null != list && list.size() > 0;
    }

    /**
     * 集合是否为空
     * 期望 空
     *
     * @param list 集合
     * @return 是否为空
     */
    public static boolean isEmpty(List<?> list) {
        return null == list || list.size() <= 0;
    }

}
