package com.basis.core.dto;


/**
* <desc>基类 </desc>
* @author wxliu
* @date 2013-5-10
* @version V1.0
 */
public class ObjectBean {
	private String id;
	private String name;
	
	public ObjectBean() {
		super();
	}
	public ObjectBean(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
