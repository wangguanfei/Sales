package com.basis.core.domain;


/**
 * Menu entity. @author MyEclipse Persistence Tools
 */

public class Menu implements java.io.Serializable {
	public final static int MAX_BIT=30;
	// Fields

	private String id;
	private String name;
	private String pid;
	private Integer degree;
	private String url;
	private Integer sort;
	private String author;

	// Constructors

	/** default constructor */
	public Menu() {
	}

	/** full constructor */
	public Menu(String name, String pid, Integer degree, String url, Integer sort) {
		this.name = name;
		this.pid = pid;
		this.degree = degree;
		this.url = url;
		this.sort = sort;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPid() {
		return this.pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public Integer getDegree() {
		return this.degree;
	}

	public void setDegree(Integer degree) {
		this.degree = degree;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getSort() {
		return this.sort;
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
	
}