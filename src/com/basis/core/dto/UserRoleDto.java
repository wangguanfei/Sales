package com.basis.core.dto;
/**
 * 用户菜单权限dto
 * @author wxliu
 *
 */
public class UserRoleDto {
	private String id;
	private String name;
	private String pid;
	private Integer degree;
	private String url;
	private Integer sort;
	private String author;
	private Integer bit;
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
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public Integer getDegree() {
		return degree;
	}
	public void setDegree(Integer degree) {
		this.degree = degree;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Integer getBit() {
		return bit;
	}
	public void setBit(Integer bit) {
		this.bit = bit;
	}
	
	
	
	
}
