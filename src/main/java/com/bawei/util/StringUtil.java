package com.bawei.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;
public class StringUtil {
	private static final int INDEX_NOT_FOUND = -1;
	private static final String NULL = "null";
	private static final String PLACEHOLDER = "\\?";
	private static char punct[] = { ',', '.', '!', '?', ';', ':', '，', '。', '！', '？', '；', '：', '、' };
	/**
	 * 手机号验证
	 * 
	 * @param str
	 * @return 验证通过返回true
	 */
	public static boolean isMobile(String str)
	{
		Pattern p = null;
		Matcher m = null;
		boolean b = false;
		p = Pattern.compile("^[1][3,4,5,6,7,8,9][0-9]{9}$"); // 验证手机号 1[3,4,5,6,7,8]9876545435
		m = p.matcher(str);
		b = m.matches();
		return b;
	}

	/**
	 * 电话号码验证
	 * 
	 * @param str
	 * @return 验证通过返回true
	 */
	public static boolean isPhone(String str)
	{
		Pattern p1 = null, p2 = null;
		Matcher m = null;
		boolean b = false;
		p1 = Pattern.compile("^[0][1-9]{2,3}-[0-9]{5,10}$"); // 验证带区号的
		p2 = Pattern.compile("^[1-9]{1}[0-9]{5,8}$"); // 验证没有区号的
		if (str.length() > 9)
		{
			m = p1.matcher(str);
			b = m.matches();
		}
		else
		{
			m = p2.matcher(str);
			b = m.matches();
		}
		return b;
	}

	/**
	 * 检查字符串是否是空白：<code>null</code>、空字符串<code>""</code>或只有空白字符。
	 * 
	 * <pre>
	 * StringUtil.isBlank(null)      = true
	 * StringUtil.isBlank("")        = true
	 * StringUtil.isBlank(" ")       = true
	 * StringUtil.isBlank("bob")     = false
	 * StringUtil.isBlank("  bob  ") = false
	 * </pre>
	 *
	 * @param str
	 *            要检查的字符串
	 *
	 * @return 如果为空白, 则返回<code>true</code>
	 */
	public static boolean isBlank(String str) {
		int length;

		if ((str == null) || ((length = str.length()) == 0)) {
			return true;
		}

		for (int i = 0; i < length; i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}
	/**
	 * 分割字符串得到子串
	 * 
	 * @param source
	 *            源字符串
	 * @param beginIndex
	 *            开始索引, 允许负数值, 表示从后往前
	 * @param endIndex
	 *            结束索引, 允许负数值, 表示从后往前
	 * @return
	 */
	public static String substring(String source, int beginIndex, int endIndex) {
		int length = source.length();
		if (beginIndex < 0) {
			beginIndex += length;
		}
		if (endIndex < 0) {
			endIndex += length;
		}
		if (beginIndex > endIndex) {
			throw new StringIndexOutOfBoundsException(
					"字符串索引超出范围: " + (endIndex - beginIndex));
		}
		return source.substring(beginIndex, endIndex);
	}

	/**
	 * 获取子串在源串中第一次出现的位置结束, 索引从0开始的串
	 * 
	 * @param source
	 *            源串
	 * @param substring
	 *            子串
	 * @return 若源串中不含有子串, 则返回一个空串
	 */
	public static String beforeString(String source, String substring) {
		int index = source.indexOf(substring);
		if (index != INDEX_NOT_FOUND) {
			return source.substring(0, index);
		}
		return "";
	}

	/**
	 * 获取子串在源串中最后一次出现的位置结束, 索引从0开始的串
	 * 
	 * @param source
	 *            源串
	 * @param substring
	 *            子串
	 * @return 若源串中不含有子串, 则返回一个空串
	 */
	public static String beforeLastString(String source, String substring) {
		int index = source.lastIndexOf(substring);
		if (index != INDEX_NOT_FOUND) {
			return source.substring(0, index);
		}
		return "";
	}

	/**
	 * 获取子串在源串中第一次出现的位置开始, 至源串末尾的串
	 * 
	 * @param source
	 *            源串
	 * @param substring
	 *            子串
	 * @return 若源串中不含有子串, 则返回一个空串
	 */
	public static String afterString(String source, String substring) {
		int index = source.indexOf(substring);
		if (index != INDEX_NOT_FOUND) {
			return source.substring(index + substring.length());
		}
		return "";
	}

	/**
	 * 获取子串在源串中最后一次出现的位置开始, 至源串末尾的串
	 * 
	 * @param source
	 *            源串
	 * @param substring
	 *            子串
	 * @return 若源串中不含有子串, 则返回一个空串
	 */
	public static String afterLastString(String source, String substring) {
		int index = source.lastIndexOf(substring);
		if (index != INDEX_NOT_FOUND) {
			return source.substring(index + substring.length());
		}
		return "";
	}

	/**
	 * 获取源串中, 两个子串之间的串
	 * 
	 * @param source
	 *            源串
	 * @param begin
	 *            子串
	 * @param end
	 *            子串
	 * @return 若源串中不含有其中任一子串, 则返回一个空串
	 */
	public static String betweenString(String source, String begin, String end) {
		int endIndex = source.indexOf(end);
		int beginIndex = source.indexOf(begin);
		if (beginIndex != INDEX_NOT_FOUND && endIndex != INDEX_NOT_FOUND) {
			return source.substring(beginIndex + begin.length(), endIndex);
		}
		return "";
	}

	/**
	 * 首字母大写
	 * 
	 * @param str
	 *            字符串
	 * @return
	 */
	public static String toCapitalize(String str) {
		byte[] bytes = str.getBytes();
		byte e = bytes[0];
		if(e >= 'a' && e <= 'z'){
			bytes[0] -= 32;
		}
		return new String(bytes);
	}

	/**
	 * 首字母小写
	 * 
	 * @param str
	 *            字符串
	 * @return
	 */
	public static String toUncapitalize(String str) {
		byte[] bytes = str.getBytes();
		byte e = bytes[0];
		if(e >= 'A' && e <= 'Z'){
			bytes[0] += 32;
		}
		return new String(bytes);
	}

	/**
	 * 以参数替换占位符[?]的形式格式化字符串
	 * 
	 * @param origin
	 *            字符串
	 * @param args
	 *            参数
	 * @return
	 */
	public static String format(String origin, Object... args) {
		for (Object arg : args) {
			origin = origin.replaceFirst(PLACEHOLDER,
					arg == null ? NULL : arg.toString());
		}
		return origin;
	}
	 /**
     * 判断字符串是否只包含unicode数字。
     *
     * <p>
     * <code>null</code>将返回<code>false</code>，空字符串<code>""</code>将返回<code>true</code>。
     * </p>
     * <pre>
     * StringUtil.isNumeric(null)   = false
     * StringUtil.isNumeric("")     = true
     * StringUtil.isNumeric("  ")   = false
     * StringUtil.isNumeric("123")  = true
     * StringUtil.isNumeric("12 3") = false
     * StringUtil.isNumeric("ab2c") = false
     * StringUtil.isNumeric("12-3") = false
     * StringUtil.isNumeric("12.3") = false
     * </pre>
     *
     * @param str 要检查的字符串
     *
     * @return 如果字符串非<code>null</code>并且全由unicode数字组成，则返回<code>true</code>
     */
    public static boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }

        int length = str.length();

        for (int i = 0; i < length; i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    /**
     * 判断字符串是否只包含unicode数字，包括小数。
     *
     * <p>
     * <code>null</code>将返回<code>false</code>，空字符串<code>""</code>将返回<code>true</code>。
     * </p>
     * <pre>
     * StringUtil.isNumeric(null)   = false
     * StringUtil.isNumeric("")     = false
     * StringUtil.isNumeric("  ")   = false
     * StringUtil.isNumeric("123")  = true
     * StringUtil.isNumeric("12 3") = false
     * StringUtil.isNumeric("ab2c") = false
     * StringUtil.isNumeric("12-3") = false
     * StringUtil.isNumeric("12.3") = true
     * </pre>
     *
     * @param str 要检查的字符串
     *
     * @return 如果字符串非<code>null</code>并且全由unicode数字组成，则返回<code>true</code>
     */
    public static boolean isNumber(String str) {
        if (isBlank(str)) {
            return false;
        }
        int index = str.indexOf(".");
        if (index < 0) {
            return isNumeric(str);
        } else {
            String num1 = str.substring(0, index);
            String num2 = str.substring(index + 1);
            return isNumeric(num1) && isNumeric(num2);
        }
    }


    /**
     * 判断字符串是否只包含unicode数字和空格<code>' '</code>。
     *
     * <p>
     * <code>null</code>将返回<code>false</code>，空字符串<code>""</code>将返回<code>true</code>。
     * </p>
     * <pre>
     * StringUtil.isNumericSpace(null)   = false
     * StringUtil.isNumericSpace("")     = true
     * StringUtil.isNumericSpace("  ")   = true
     * StringUtil.isNumericSpace("123")  = true
     * StringUtil.isNumericSpace("12 3") = true
     * StringUtil.isNumericSpace("ab2c") = false
     * StringUtil.isNumericSpace("12-3") = false
     * StringUtil.isNumericSpace("12.3") = false
     * </pre>
     *
     * @param str 要检查的字符串
     *
     * @return 如果字符串非<code>null</code>并且全由unicode数字和空格组成，则返回<code>true</code>
     */
    public static boolean isNumericSpace(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!Character.isDigit(str.charAt(i)) && (str.charAt(i) != ' ')) {
                return false;
            }
        }
        return true;
    }
    /**
     * 将字符串中的标点符号过滤掉
     * 
     * @param str
     * @return
     */
    public static String TrimPunctuation(String str) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < str.length(); ++i) {
            boolean need_filter = false;
            for (int j = 0; j < punct.length; ++j) {
                if (punct[j] == str.charAt(i)) {
                    need_filter = true;
                    break;
                }
            }

            if (!need_filter) {
                result.append(str.charAt(i));
            }
        }

        return result.toString();
    }

    /**
     * 判断是否包含标点
     */
    public static boolean ContainsPunctuation(String str) {
        for (int i = 0; i < str.length(); ++i) {
            for (int j = 0; j < punct.length; ++j) {
                if (punct[j] == str.charAt(i)) {
                    return true;
                }
            }
        }

        return false;
    }
   
}
