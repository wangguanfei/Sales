package com.basis.core.constants;
/**
 * @description : 用户状态
 * @author : wxliu
 */
public enum EEmployeeState {
	ALL(-1),//所有
	DELETE(0),//作废
	NORMAL(1),//正常
	LOCK(2);//冻结
	private int code;

    EEmployeeState(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
    public static EEmployeeState returnEnum(int code){
    	switch(code){
	    	case 0:return DELETE;
	    	case 1:return NORMAL;
	    	case 2:return LOCK;
	    	case -1:return ALL;
    	}
    	return null;
    }
}
