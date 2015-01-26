package com.basis.core.domain;

import java.util.Date;
import java.util.Set;

import org.apache.struts2.json.annotations.JSON;

/**
 * Role entity. @author MyEclipse Persistence Tools
 */

public class Role implements java.io.Serializable {

	// Fields

	private String id;
	private String name;
	private String templateId;
	private String remark;
	private Integer state;
	private Integer sort;
	private String createrId;
	private Date createTime;
	private String modifyerId;
	private Date modifyTime;
	private Roletemplate roletemplate;
	private Set<RoleMenu> roleMenuSet;
	private Employee creater;
	private Employee modifyer;
	
	//级别类型
	private Integer gradeType;
	//项目类型
	private Integer projectType;
	
	// Constructors

	/** default constructor */
	public Role() {
	}

	/** full constructor */
	public Role(String name,
			String templateId, String remark, Integer state, Integer sort,
			String createrId, Date createTime, String modifyerId,
			Date modifyTime) {
		this.name = name;
		this.templateId = templateId;
		this.remark = remark;
		this.state = state;
		this.sort = sort;
		this.createrId = createrId;
		this.createTime = createTime;
		this.modifyerId = modifyerId;
		this.modifyTime = modifyTime;
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

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getCreaterId() {
		return createrId;
	}

	public void setCreaterId(String createrId) {
		this.createrId = createrId;
	}
	@JSON(format="yyyy-MM-dd")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getModifyerId() {
		return modifyerId;
	}

	public void setModifyerId(String modifyerId) {
		this.modifyerId = modifyerId;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Roletemplate getRoletemplate() {
		return roletemplate;
	}

	public void setRoletemplate(Roletemplate roletemplate) {
		this.roletemplate = roletemplate;
	}

	public Set<RoleMenu> getRoleMenuSet() {
		return roleMenuSet;
	}

	public void setRoleMenuSet(Set<RoleMenu> roleMenuSet) {
		this.roleMenuSet = roleMenuSet;
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

	public Integer getGradeType() {
		return gradeType;
	}

	public void setGradeType(Integer gradeType) {
		this.gradeType = gradeType;
	}

	public Integer getProjectType() {
		return projectType;
	}

	public void setProjectType(Integer projectType) {
		this.projectType = projectType;
	}

	
	// Property accessors


}