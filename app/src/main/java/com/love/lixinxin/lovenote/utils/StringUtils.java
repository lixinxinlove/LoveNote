package com.love.lixinxin.lovenote.utils;

import android.graphics.drawable.AnimationDrawable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.widget.ImageView;

import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

	/**
	 * 是否为空字符串
	 */
	public static boolean isNull(String str) {
		if (null == str || "".equals(str.trim()) || "null".equals(str.trim())) {
			return true;
		}
		return false;
	}

	public static boolean isNotNull(String str) {
		if (null == str || "".equals(str.trim()) || "null".equals(str.trim())) {
			return false;
		}
		return true;
	}

	public static boolean checkEmail(String email) {
		boolean flag = false;
		try {
			String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher(email);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}

		return flag;
	}

	private static final double GB = 1024L * 1024L * 1024L;// 定义GB的计算常量
	private static final double MB = 1024L * 1024L;// 定义MB的计算常量
	private static final double KB = 1024L;// 定义KB的计算常量

	public static String byteConversionGBMBKB(double kSize) {
		DecimalFormat df = new DecimalFormat("#.00");
		double temp = 0;
		if (kSize / GB >= 1) {
			temp = kSize / GB;
			return df.format(temp) + "GB";
		} else if (kSize / MB >= 1) {
			temp = kSize / MB;
			return df.format(temp) + "MB";
		} else if (kSize / KB >= 1) {
			temp = kSize / KB;
			return df.format(temp) + "KB";
		}
		return kSize + "B";
	}

	/**
	 * 半角转换为全角
	 * 
	 * @param input
	 * @return
	 */
	public static String ToSBC(String input) {
		char[] c = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (isChinese(c[i])) {
				if (c[i] == 12288) {
					c[i] = (char) 32;
					continue;
				}
				if (c[i] > 65280 && c[i] < 65375)
					c[i] = (char) (c[i] - 65248);
			}
		}
		return new String(c);
	}

	private static boolean isChinese(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
				|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
				|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
				|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
			return true;
		}
		return false;
	}

	public static int floatToInt(float source) {
		return new BigDecimal(source).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
	}

	/**
	 *
	 * @param format
	 *            "yyyy-MM-dd HH:mm:ss"
	 * @return
	 */
	public static String getDateFormat(String format) {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	public static String getTokenMD5(String resouce) {
		String reStr = null;
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			byte[] bytes = md5.digest(resouce.getBytes());
			StringBuffer stringBuffer = new StringBuffer();
			for (byte b : bytes) {
				int bt = b & 0xff;
				if (bt < 16) {
					stringBuffer.append(0);
				}
				stringBuffer.append(Integer.toHexString(bt));
			}
			reStr = stringBuffer.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return reStr;
	}

	// public static String decimalFormat(double price) {
	// DecimalFormat df = new DecimalFormat("######0");
	// return df.format(price);
	// }

	// public static String priceFormat(String price) {
	// if (isNotNull(price) && price.indexOf(".") != -1) {
	// return price.substring(0, price.indexOf("."));
	// }
	// return price;
	// }

	public static String priceNewFormat(String price) {
		if (StringUtils.isNull(price)) {
			return "0";
		}
		DecimalFormat df = new DecimalFormat("#########.##");
		double priceDouble = 0;
		try {
			priceDouble = Double.parseDouble(price);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return df.format(priceDouble);
	}

	public static Spannable getPriceSpannable(String source) {
		int indexOf = source.indexOf(".");
		Spannable span = null;
		if (indexOf != -1) {
			span = new SpannableString(source);
			span.setSpan(new AbsoluteSizeSpan(12, true), indexOf, source.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		} else {
			source += ".00";
			span = new SpannableString(source);
			span.setSpan(new AbsoluteSizeSpan(12, true), source.indexOf("."), source.length(),
					Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		}
		return span;
	}

	public static String priceNewFormat(double price) {
		DecimalFormat df = new DecimalFormat("#########.##");

		return df.format(price);
	}

	public static String priceNewFormat(double price, String format) {
		DecimalFormat df = new DecimalFormat(format);
		return df.format(price);

	}

	public static boolean isMobileNO(String mobiles, String pattern) {
		boolean flag = false;
		try {
			Pattern p = Pattern.compile(pattern);
			Matcher m = p.matcher(mobiles);
			flag = m.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	/**
	 * 解析出url参数中的键值对 如 "index.jsp?Action=del&id=123"，解析出Action:del,id:123存入map中
	 * 
	 * @param URL
	 *            url地址
	 * @return url请求参数部分
	 */
	public static Map<String, String> URLRequest(String URL) {
		Map<String, String> mapRequest = new HashMap<String, String>();

		String[] arrSplit = null;

		String strUrlParam = TruncateUrlPage(URL);
		if (strUrlParam == null) {
			return mapRequest;
		}
		strUrlParam = strUrlParam.replaceAll("&amp;", "&");
		arrSplit = strUrlParam.split("[&]");
		for (String strSplit : arrSplit) {
			String[] arrSplitEqual = null;
			arrSplitEqual = strSplit.split("[=]");

			// 解析出键值
			if (arrSplitEqual.length > 1) {
				String key = arrSplitEqual[0];
				// if (key.lastIndexOf(";") != -1) {
				// key = key.substring(key.indexOf(";") + 1, key.length());
				// }
				// 正确解析
				mapRequest.put(key, arrSplitEqual[1]);

			} else {
				if (arrSplitEqual[0] != "") {
					// 只有参数没有值，不加入
					mapRequest.put(arrSplitEqual[0], "");
				}
			}
		}
		return mapRequest;
	}

	/**
	 * 去掉url中的路径，留下请求参数部分
	 * 
	 * @param strURL
	 *            url地址
	 * @return url请求参数部分
	 */
	private static String TruncateUrlPage(String strURL) {
		String strAllParam = null;
		String[] arrSplit = null;
		strURL = strURL.trim().toLowerCase();
		arrSplit = strURL.split("[?]");
		if (strURL.length() > 1) {
			if (arrSplit.length > 1) {
				if (arrSplit[1] != null) {
					strAllParam = arrSplit[1];
				}
			}
		}

		return strAllParam;
	}

	public static String getHideString(String number, int start, int end) {
		if (isNotNull(number)) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < number.length(); i++) {
				char c = number.charAt(i);
				if (i >= start && i <= end) {
					sb.append('*');
				} else {
					sb.append(c);
				}
			}
			return sb.toString();
		}
		return number;
	}

	public static String getHideEmailString(String email) {
		String[] emails = email.split("@");
		if (emails.length > 1) {
			String emailsString = emails[0];
			if (isNotNull(emailsString)) {
				int length = emailsString.length();
				if (length > 3) {
					return getHideString(emailsString, 2, emailsString.length() - 2) + "@" + emails[1];
				} else if (length == 3) {
					return emailsString.substring(0, 1) + "**@" + emails[1];
				} else if (length == 2) {
					return emailsString.substring(0, 1) + "*@" + emails[1];
				} else {
					return "*@" + emails[1];
				}
			}
		}
		return "";
	}

	public static String getShowTime(long milliseconds) {
		// 获取日历函数
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(milliseconds);
		SimpleDateFormat dateFormat = null;
		// 判断是否大于60分钟，如果大于就显示小时。设置日期格式
		if (milliseconds / 60000 > 60) {
			dateFormat = new SimpleDateFormat("hh:mm:ss");
		} else {
			dateFormat = new SimpleDateFormat("00:mm:ss");
		}
		return dateFormat.format(calendar.getTime());
	}

	public static void startImageAnim(ImageView Img, int anim) {
		Img.setVisibility(View.VISIBLE);
		try {
			Img.setImageResource(anim);
			AnimationDrawable animationDrawable = (AnimationDrawable) Img.getDrawable();
			animationDrawable.start();
		} catch (ClassCastException e) {
			e.printStackTrace();
		}
	}

	public static void stopImageAnim(ImageView Img) {
		try {
			AnimationDrawable animationDrawable = (AnimationDrawable) Img.getDrawable();
			animationDrawable.stop();
		} catch (ClassCastException e) {
			e.printStackTrace();
		}
		Img.setVisibility(View.GONE);
	}

}
