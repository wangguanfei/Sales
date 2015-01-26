package com.basis.core.domain;

import java.util.Date;
import java.util.Set;

import org.apache.struts2.json.annotations.JSON;

/**
 * Roletemplate entity. @author MyEclipse Persistence Tools
 */

public class Roletemplate implements java.io.Serializable {

	// Fields

	private String id;
	private String name;
	private Integer state;
	private Integer sort;
	private String createrId;
	private Date createTime;
	private String modifyerId;
	private Date modifyTime;
	private Set roletemplateMenuSet;
	private Employee creater;
	private Employee modifyer;

	// Constructors

	/** default constructor */
	public Roletemplate() {
	}

	/** full constructor */
	public Roletemplate(String name, Integer state, Integer sort,
			String createrId, Date createTime, String modifyerId,
			Date modifyTime) {
		this.name = name;
		this.state = state;
		this.sort = sort;
		this.createrId = createrId;
		this.createTime = createTime;
		this.modifyerId = modifyerId;
		this.modifyTime = modifyTime;
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

	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getSort() {
		return this.sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getCreaterId() {
		return this.createrId;
	}

	public void setCreaterId(String createrId) {
		this.createrId = createrId;
	}
	@JSON(format="yyyy-MM-dd")
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getModifyerId() {
		return this.modifyerId;
	}

	public void setModifyerId(String modifyerId) {
		this.modifyerId = modifyerId;
	}
	@JSON(format="yyyy-MM-dd")
	public Date getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Set getRoletemplateMenuSet() {
		return roletemplateMenuSet;
	}

	public void setRoletemplateMenuSet(Set roletemplateMenuSet) {
		this.roletemplateMenuSet = roletemplateMenuSet;
	}

	public Employee getCreater() {
		return creater;
	}

	public void setCreater(Employee creater) {
		this.creater = creater;
	}

	public Employee getModifyer() {
		return modifyer;
	}

	public void setModifyer(Employee modifyer) {
		this.modifyer = modifyer;
	}
	

}