package com.basis.core.constants;
/**
 * @description : 全局boolean状态
 * @author : wxliu
 */
public enum EBooleanCode {
	ALL(-1),//所有
	NO(0),//否
	YES(1);//是
	private int code;

    EBooleanCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
    public static EBooleanCode returnEnum(int code){
    	switch(code){
	    	case 0:return NO;
	    	case 1:return YES;
	    	case -1:return ALL;
    	}
    	return null;
    }
}
