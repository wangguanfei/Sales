package com.basis.core.constants;
/**
 * @description : 返回消息名称  参照 basis/classes/message_zh_CN.properties
 * @author : wxliu
 */
public enum EMessageCode {
	SUCCESS("message.success"),
	FAIL("message.fail"),
	EXCEPTION("message.exception"),
	PARAMISNULL("message.param.isnull"),
	NAMEISEXIST("message.name.isexist"),
	SORTISEXIST("message.sort.isexist");
	
	private String code;

    EMessageCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }
}
