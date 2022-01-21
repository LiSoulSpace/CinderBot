package xyz.soulspace.cinder.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomUtil {
    /**
     * 随机一个正整数
     * 最小为0
     * 最大以传参为准
     *
     * @param maxNum 随机出的最大数
     * @return 一个符合条件的随机数
     */
    public static int roll(int maxNum) {
        //由于不会随机到入参本身，所以需要+1
        return new Random().nextInt(maxNum + 1);
    }

    /**
     * 随机多个正整数
     * 最小为0
     * 最大以传参为准
     *
     * @param maxNum 随机出的最大数
     * @param count  返回元素数量
     * @return 一个符合条件的随机数
     */
    public static List<Integer> roll(int maxNum, int count) {
        //返回的元素数量不能大于可随机的元素数量
        if (count > maxNum) {
            return null;
        }
        List<Integer> randNumList = new ArrayList<>();
        int i = 1;
        do {
            do {
                //由于不会随机到入参本身，所以需要+1
                Integer randNum = roll(maxNum);
                if (!randNumList.contains(randNum)) {
                    randNumList.add(randNum);
                    break;
                }
            } while (true);
            i++;
        } while (count >= i);
        return randNumList;
    }

    /**
     * 返回列表中的一条随机字符串
     *
     * @param strList 目标列表
     * @return 列表中的随机一条
     */
    public static String rollStrFromList(List<String> strList) {
        if (null == strList || strList.size() <= 0) {
            return "";
        }
        //获取消息
        String msg = strList.get(RandomUtil.roll(strList.size() - 1));
        //针对换行符做处理
        return msg.replaceAll("\\\\n", "\n");
    }
}
