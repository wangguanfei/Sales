package com.basis.core.constants;
/**
 * @description : 业务操作类型
 * @author : wxliu
 */
public enum EOperator {
	ADD(1),//增加
	DELETE(2),//删除
	MODIFY(3),//修改
	SELECT(4);//查询
	private int code;

    EOperator(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
    public static EOperator returnEnum(int code){
    	switch(code){
	    	case 1:return ADD;
	    	case 2:return DELETE;
	    	case 3:return MODIFY;
	    	case 4:return SELECT;
    	}
    	return null;
    }
}
