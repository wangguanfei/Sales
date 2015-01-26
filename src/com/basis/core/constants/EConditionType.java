package com.basis.core.constants;
/**
 * @description : 全局状态
 * @author : wxliu
 */
public enum EConditionType {
	LIST(1),//查询列表
	COUNT(2),//查询记录数
	STATISTICS(3),//统计
	MODIFY(4),//修改
	VALID(5);//验证
	private int code;

    EConditionType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
    public static EConditionType returnEnum(int code){
    	switch(code){
	    	case 1:return LIST;
	    	case 2:return COUNT;
	    	case 3:return STATISTICS;
	    	case 4:return MODIFY;
	    	case 5:return VALID;
    	}
    	return null;
    }
}
