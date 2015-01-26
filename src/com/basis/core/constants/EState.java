package com.basis.core.constants;
/**
 * @description : 全局状态
 * @author : wxliu
 */
public enum EState {
	ALL(-1),//所有
	DELETE(0),//作废
	NORMAL(1);//正常
	private int code;

    EState(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
    public static EState returnEnum(int code){
    	switch(code){
	    	case 0:return DELETE;
	    	case 1:return NORMAL;
	    	case -1:return ALL;
    	}
    	return null;
    }
}
