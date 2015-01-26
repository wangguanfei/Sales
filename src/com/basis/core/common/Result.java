package com.basis.core.common;

import javax.transaction.SystemException;

import com.basis.core.constants.EMessageCode;

/**
 * 结果类：service 或者 action 返回值 
 * @author wxliu
 */
public class Result<T extends Object> {
	
	private boolean success;//操作 是否成功
	private String code;//返回值编号   参照basis-web/classes/message_zh_CN.properties
	private String message;//返回消息
	private T data;//返回附加信息 可以是String、List、JSON、Map 类型
	
	public Result(boolean success) {
		this.success = success;
		if(success){
			this.setCode(EMessageCode.SUCCESS.getCode());//操作成功
		}else{
			this.setCode(EMessageCode.FAIL.getCode());//操作失败
		}
		
	}
	public Result(boolean success, String code) {
		super();
		this.success = success;
		this.code = code;
	}
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public void initErrorMessage(){
		new SystemException();
	}
	
}
