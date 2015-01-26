package com.basis.core.util;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.basis.core.exception.ParamIsNullException;
/**
 * 
 * @author wxliu
 * 常用工具类
 */
public class BasisUtil {
	/**
	 * 判断参数不能为空，如果为空抛出异常
	 * @param params
	 * @throws ParamIsNullException
	 */
	public static void validParamsNotNull(Object... params) throws ParamIsNullException {
		if (params == null || params.length < 1){
				return;
		}
		for (Object o:params) {
			if (o == null){
				throw new ParamIsNullException("Param cannot be null.");
			}
			if (o instanceof String){
				if(StringUtils.isBlank((String)o)){
					throw new ParamIsNullException("Param cannot be null.");
				}
			}
		}
	}
	/**
	 * 左位移之和
	 * @param list
	 * @return
	 */
	public static Long bitToLong(List list){
		if(list==null&&list.size()==0){
			return 0l;
		}
		Long bit=0l;
		for(Object b:list){
			if(b instanceof Integer){
				bit=bit+bitToLong((Integer)b);
			}
			else if(b instanceof String){
				bit=bit+bitToLong(Integer.valueOf(b.toString()));
			}
		}
		return bit;
	}
	/**
	 * 左位移
	 * @param b
	 * @return
	 */
	public static Long bitToLong(int b){
		if(b==0)return 0l;
		return (long)((long)1<<b);
	}
	/**
	 * null转化成空字符串""
	 * @param s
	 * @return
	 */
	public static String nullToString(String s){
		return StringUtils.isBlank(s)?"":s;
	}
	/**
	 * 空字符串""转化成null
	 * @param s
	 * @return
	 */
	public static String stringToNull(String s){
		return StringUtils.isBlank(s)?null:s;
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
	/**
	* <desc> 将对象加入到一个List列表中</desc>
	* @author wxliu
	* @param obj
	* @return
	* <history> </history>
	 */
	public static List addToList(Object obj){
		List l=new ArrayList();
		if(obj!=null){
			if(obj instanceof String){
				if(StringUtils.isNotBlank(obj.toString())){
					l.add(obj);
				}
			}else{
				l.add(obj);
			}
		}
		return l;
	}

}
