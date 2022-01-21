package xyz.soulspace.cinder.utils;

public class StringUtil {
    public static boolean isEmpty(String str) {
        return str == null;
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 去掉字符串中的所有空格
     *
     * @param str 源字符串
     * @return 去空格后的字符串
     */
    public static String trimAll(String str) {
        if (str == null)
            return "";
        return str.replaceAll(" ", "");
    }

    /**
     * 移除前面的空格
     *
     * @param original 源字符串
     * @return 移除后的字符串
     */
    public static String removeStartingSpace(String original) {
        while (original.startsWith(" ")) {
            original = original.substring(1);
        }
        return original;
    }
}
