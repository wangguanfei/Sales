package com.basis.core.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;


public class StringUtil {
	static Map<String, String> teacherIds = null;
	static {
		teacherIds = new HashMap<String, String>();
		teacherIds.put("0F2992414709405993FC2C2E6C89036B", "#FF0000");// A
		teacherIds.put("9E42DD1EBF984F21B64D7283E34E4D24", "#FF0000");// a
		teacherIds.put("9E788AC9EC5D48999CA7CE048DDA4605", "#FF9C00");// B
		teacherIds.put("D7CEF2A958A84E62A0A0F78090C1142E", "#FF9C00");// b
		teacherIds.put("F4EBC26D1120483DB7902C4399EE17DD", "#4D9904");// C
		teacherIds.put("E680CA4D62C940D298B7B3BFA9647D7F", "#4D9904");// c
		teacherIds.put("E5383F8E79D44E1D9A4C167DBC32B09C", "#000000");// D
		teacherIds.put("923D5ABC163448D3A916745EF4495E15", "#000000");// d
		teacherIds.put("E1C2DC5929434A20BEC15075A231431A", "#CC00FF");// S
		teacherIds.put("CDCA8A9645534DFA823F800A37A5706D", "#CC00FF");// s
	}

	public static String getTeacherColour(String key) {
		return teacherIds.get(key);
	}

	public final static String EMAIL_PATTERN_STRING = "^([\\w-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([\\w-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
	public final static String PASSWORD_PATTERN_STRING = "^(?![^a-zA-Z]+$)(?!\\D+$).{8,20}$";// 2011-6-7
																								// RJX
																								// 修改密码长度
																								// {8}变为{8,20}
	public final static Pattern EMAIL_PATTERN = Pattern.compile(
			EMAIL_PATTERN_STRING, Pattern.CASE_INSENSITIVE);
	public final static Pattern PASSWORD_PATTERN = Pattern.compile(
			PASSWORD_PATTERN_STRING, Pattern.CASE_INSENSITIVE);

	public static String stringFormat(String str, int toCount, String more) {
		if (str == null)
			return "";
		StringBuffer reStr = new StringBuffer();
		char[] tempChar = str.toCharArray();
		int i = 0;
		for (int j = 0; i < tempChar.length && j <= toCount; i++) {
			String t1 = String.valueOf(tempChar[i]);
			j += t1.replaceAll("[\u00ff-\uffff]", "**").getBytes().length;
			if (j > toCount) {
				break;
			}
			reStr.append(t1);
		}
		if (tempChar.length > i) {
			reStr.append(more);
		}
		return reStr.toString();
	}

	public static boolean stringIsNull(String str) {
		if (str == null || "".equals(str.trim())) {
			return true;
		}
		return false;
	}

	public static boolean stringIsNullFormTrimSp(String str) {
		if (str == null || "".equals(str)) {
			if (!"".equals(str.trim()))
				return true;
		}
		return false;
	}

	public static boolean stringLengthOut(String str, int min, int max) {
		if (str == null)
			return true;
		int strLength = str.replaceAll("[\u00ff-\uffff]", "**").length();
		if (max == 0)
			max = Integer.MAX_VALUE;
		if (strLength >= min && strLength <= max) {
			return false;
		}
		return true;
	}

	public static String returnRegexStr(String oldStr, String regex, int i) {
		if (oldStr == null) {
			return "";
		}
		String str = "";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(oldStr);
		if (m.find() && i <= m.groupCount()) {
			str = m.group(i);
		}
		return str;
	}

	public static String toUpperCaseFirstLetter(String s) {

		return stringIsNull(s) ? s : s.substring(0, 1).toUpperCase()
				+ (s.length() > 1 ? s.substring(1) : "");
	}

	/**
	 * 判断字符串集中是否有重复的字符串
	 * 
	 * @param ss
	 * @return
	 */
	public static boolean haveRepeatStr(List<String> ss) {
		boolean flag = false;
		List<String> strs = new ArrayList<String>();
		for (String s : ss) {
			if (strs.contains(s)) {
				flag = true;
				break;
			}
			strs.add(s);
		}
		return flag;
	}

	/**
	 * 判断是否为电话号码
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isTelcode(String str) {
		for (int i = 0; i < str.length(); i++) {
			if ((Character.isDigit(str.charAt(i)) == false)
					&& (str.charAt(i) != '-')) {
				return false;
			}
			// if (i == 0 && str.charAt(i) != '0') {
			// return false;
			// }
		}
		if (str.length() > 18) {
			return false;
		}
		return true;
	}

	/**
	 * 字符编码，根据字符格式
	 * 
	 * @param str
	 *            要编码的字符串
	 * @param enc
	 *            字符格式(UTF-8,GBK等)
	 * @return
	 */
	public static String encodeString(String str, String enc) {
		String temp = "";
		try {
			temp = java.net.URLEncoder.encode(str, enc);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return temp;
	}

	/**
	 * 字符解码，根据字符格式
	 * 
	 * @param str
	 *            要编码的字符串
	 * @param enc
	 *            字符格式(UTF-8,GBK等)
	 * @return
	 */
	public static String decodeString(String str, String enc) {
		String temp = "";
		try {
			temp = java.net.URLDecoder.decode(str, enc);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return temp;
	}

	/**
	 * 字符转Long
	 */
	public static Long StringToLong(List<Integer> bitNumber) {
		Long tl = 0l;
		for (int ti : bitNumber) {
			tl = tl ^ (1l << (64 - ti - 1));
		}
		return tl;
	}

	/**
	 * 全角转半角
	 * 
	 * @param input
	 *            String.
	 * @return 半角字符串
	 */
	public static String ToDBC(String input) {

		char c[] = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == '\u3000') {
				c[i] = ' ';
			} else if (c[i] > '\uFF00' && c[i] < '\uFF5F') {
				c[i] = (char) (c[i] - 65248);

			}
		}
		String returnString = new String(c);

		return returnString;
	}

	/**
	 * 是否为数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9\\s]*");
		return pattern.matcher(str).matches();
	}

	/**
	 * 是否为字母
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isChar(String str) {
		Pattern pattern = Pattern.compile("[A-Za-z]\\s*");
		return pattern.matcher(str).matches();
	}

	/**
	 * 判断是否为字母或数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isCharOrNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9A-Za-z]*");
		return pattern.matcher(str).matches();
	}

	/**
	 * 判断是否合法的email地址
	 * 
	 * @param email
	 *            String
	 * @return boolean
	 */
	public static boolean isValidEmail(String email) {
		if (stringIsNull(email)) {
			return false;
		}
		return StringUtil.EMAIL_PATTERN.matcher(email).matches();
	}

	/**
	 * 验证密码是否合法（8位字母和数字组成且必须包含字母与数字）
	 * <p>
	 * 作者:lenovo
	 * </p>
	 * <p>
	 * 功能描述:
	 * </p>
	 * <p>
	 * 创建时间:4:05:43 PM
	 * </p>
	 * <p>
	 * @param password
	 * <p>
	 * @return
	 * </p>
	 * <p>
	 * 修改:
	 * </p>
	 */
	public static boolean isValidPassword(String password) {
		if (StringUtils.isBlank(password)) {
			return false;
		}
		return PASSWORD_PATTERN.matcher(password).matches();
	}

	public static String complement0(String num, int numlength) {
		while (num.length() < numlength)
			num = "0" + num;
		return num;
	}

	/**
	 * 得到一个字符串的字节长度（汉字按照2个字节处理）
	 * 
	 * @param input
	 *            String
	 * @return int
	 */
	public static int getByteLength(String input) {
		if (StringUtils.isBlank(input)) {
			return 0;
		}
		int length = 0;
		for (int i = 0, size = input.length(); i < size; i++) {
			if (input.charAt(i) > 255) {
				length += 2;
			} else {
				length++;
			}
		}

		return length;
	}

	/**
	 * 获得固定字节长度的字符串(不够右侧补0)
	 * 
	 * @param str
	 * @param byteLength
	 * @return
	 */
	public static String getSubStringOfByte0(String str, int byteLength) {
		String stremp = "";
		if (getByteLength(str) > byteLength) {// 截取固定字节长度，汉字是两个字节
			char[] ch = str.toCharArray();
			for (int i = 0; i < ch.length; i++) {
				int j = getByteLength("" + ch[i]);
				if (getByteLength(stremp) + j > byteLength) {
					return getByteComplement0(stremp, byteLength);
				} else if (getByteLength(stremp) + j == byteLength) {
					return stremp + ch[i];
				} else {
					stremp += ch[i];
				}
			}
		} else {
			return getByteComplement0(str, byteLength);
		}
		return getByteComplement0(stremp, byteLength);
	}

	/**
	 * 获得固定字节长度的字符串(不够右侧补" ")
	 * 
	 * @param str
	 * @param byteLength
	 * @return
	 */
	public static String getSubStringOfByte_(String str, int byteLength) {
		String stremp = "";
		if (getByteLength(str) > byteLength) {// 截取固定字节长度，汉字是两个字节
			char[] ch = str.toCharArray();
			for (int i = 0; i < ch.length; i++) {
				int j = getByteLength("" + ch[i]);
				if (getByteLength(stremp) + j > byteLength) {
					return getByteComplement_(stremp, byteLength);
				} else if (getByteLength(stremp) + j == byteLength) {
					return stremp + ch[i];
				} else {
					stremp += ch[i];
				}
			}
		} else {
			return getByteComplement_(str, byteLength);
		}
		return getByteComplement_(stremp, byteLength);
	}

	public static String getByteComplement0(String num, int numlength) {
		while (num.getBytes().length < numlength)
			num = "0" + num;
		return num;
	}

	public static String getByteComplement_(String num, int numlength) {
		while (num.getBytes().length < numlength)
			num = " " + num;
		return num;
	}

	public static String generateString() {
		String s = UUID.randomUUID().toString();
		return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18)
				+ s.substring(19, 23) + s.substring(24);
	}

	/**
	 * 生成随机数字和字母的字符串
	 * <p>
	 * 作者:lenovo
	 * </p>
	 * <p>
	 * 功能描述:
	 * </p>
	 * <p>
	 * 创建时间:11:35:30 AM
	 * </p>
	 * <p>
	 * 
	 * @param length
	 *            <p>
	 * @return </p>
	 *         <p>
	 *         修改:
	 *         </p>
	 */
	public static String getCharacterAndNumber(int length) {
		String val = "";
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num"; // 输出字母还是数字
			if ("char".equalsIgnoreCase(charOrNum)) {// 字符串
				int choice = random.nextInt(2) % 2 == 0 ? 65 : 97; // 取得大写字母还是小写字母
				val += (char) (choice + random.nextInt(26));
			} else if ("num".equalsIgnoreCase(charOrNum)) {// 数字

				val += String.valueOf(random.nextInt(10));
			}
		}
		return val;
	}

	/**
	 * 
	 * <p>
	 * 作者:liuhongtao
	 * </p>
	 * <p>
	 * 功能描述:针对多个id的字段,为数据添加引号
	 * </p>
	 * <p>
	 * 创建时间:20112011-7-26上午10:05:46
	 * </p>
	 * <p>
	 * @param fild
	 * <p>
	 * @return
	 * </p>
	 * <p>
	 * 修改:
	 * </p>
	 */
	public static String addQuotesToFild(String filds) {
		return "'" + filds.replaceAll(",", "','") + "'";
	}

	/**
	 * 
	 * <p>
	 * 作者:liuhongtao
	 * </p>
	 * <p>
	 * 功能描述:判断String数组为空
	 * </p>
	 * <p>
	 * 创建时间:20112011-12-14下午06:29:34
	 * </p>
	 * <p>
	 * @param sList
	 * <p>
	 * @return
	 * </p>
	 * <p>
	 * 修改:
	 * </p>
	 */
	public static boolean StringArrayIsNotNull(String[] sList) {
		if (sList == null)
			return false;
		boolean isNotNull = false;
		for (String iterm : sList) {
			if (!stringIsNull(iterm))
				isNotNull = true;
		}
		return isNotNull;
	}

	
	/**
	 * 读取inputstream
	 * <p>作者:wangxida</p>
	 * <p>功能描述:</p>
	 * <p>创建时间:20122012-4-9下午03:19:40</p>
	 * <p>@param is
	 * <p>@return
	 * <p>@throws IOException</p>
	 * <p>修改:</p>
	 */
	public static String inputStream2String(InputStream is) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int i = -1;
		while ((i = is.read()) != -1) {
			baos.write(i);
		}
		return baos.toString();
	}

	 /**
     * 
     * <p>作者:luhuainan</p>
     * <p>功能描述:判断String只含有文字英文数字</p>
     * <p>创建时间:20112011-12-14下午06:29:34</p>
     * <p>@param sList
     * <p>@return</p>
     * <p>修改:</p>
     */
    public static boolean StringIsNotSign(String str){
    	
    	Pattern pattern = Pattern.compile("^[\\u4e00-\\u9fa5a-zA-Z0-9]*$");  
 	    return pattern.matcher(str).matches();    
    }
    /**
     * 保留textarea样式 换行 空格等
     * @param mystr
     * @return
     */
    public static String MyReplace(String mystr) {
		if (mystr == null || mystr == "") {
			return ("&nbsp;");
		} else {
			mystr = mystr.replace("\n\r", "<br>");
			mystr = mystr.replace("\r", "<br>");
			mystr = mystr.replace("\t", "　　");
			return (mystr);
		}
	}
	public final static String getMD5(String s){
		char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd','e', 'f'};
		try {
			byte[] strTemp = s.getBytes("utf-8");
			
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>>4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		}
		catch (Exception e){
			return null;
		}
	}
	public static void main(String[] args) {
		System.out.println(getMD5("1403159465119"+"12380"));
	}
	
	  public static  String parseToPath(String str) {
	        str = str.replace('\\', '/');
	        str = str.replaceAll("/{2,}","/");
	        return str;
	    }
}
