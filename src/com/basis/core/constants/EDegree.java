package com.basis.core.constants;
/**
 * @description : 全局状态
 * @author : wxliu
 */
public enum EDegree {
	ONE(1),//一级
	TWO(2),//二级
	THREE(3);//三级
	private int code;

    EDegree(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
    public static EDegree returnEnum(int code){
    	switch(code){
	    	case 1:return ONE;
	    	case 2:return TWO;
	    	case 3:return THREE;
    	}
    	return null;
    }
}
