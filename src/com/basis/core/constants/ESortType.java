package com.basis.core.constants;
/**
 * @description : 数据库查询排序类型
 * @author : wxliu
 */
public enum ESortType {
	ASC("ASC"),//升序
	DESC("DESC");//降序
	private String code;

    ESortType(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }
}
