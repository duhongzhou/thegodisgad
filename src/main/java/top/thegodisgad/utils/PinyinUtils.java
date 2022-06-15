package top.thegodisgad.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * @author yhdhz
 */
public class PinyinUtils {
    public static StringBuilder getFirstCapitalLetter(String str)
    {
        char[] charArray = str.toCharArray();
        StringBuilder pinyin = new StringBuilder();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        //设置大小写格式
        defaultFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        //设置声调格式：
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (char c : charArray) {
            //匹配中文,非中文转换会转换成null
            if (Character.toString(c).matches("[\\u4E00-\\u9FA5]+")) {
                String[] hanyuPinyinStringArray = new String[0];
                try {
                    hanyuPinyinStringArray = PinyinHelper.toHanyuPinyinStringArray(c, defaultFormat);
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    throw new RuntimeException(e);
                }
                char[] string = hanyuPinyinStringArray[0].toCharArray();
                pinyin.append(string[0]);
            } else {
                pinyin.append(c);
            }
        }
        return pinyin;
    }
}
