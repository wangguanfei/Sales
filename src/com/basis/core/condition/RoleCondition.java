package com.basis.core.condition;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;

import com.basis.core.common.Condition;
import com.basis.core.constants.ESortType;

public class RoleCondition extends Condition{
	private String templateId;
	private String likename;
	//级别类型
	private Integer gradeType;
	//项目类型
	private Integer projectType;
	
	@Override
	public String getInitialHql() {
		StringBuffer buffer =new StringBuffer();
		buffer.append("select role from Role role where role.state=1 ");
		if(StringUtils.isNotBlank(templateId)){
			buffer.append(" and role.templateId=:templateId ");
		}
		if(StringUtils.isNotBlank(likename)){
			buffer.append(" and role.name like :likename ");
		}
		if(gradeType != null){
			buffer.append(" and role.gradeType =:gradeType ");
		}
		if(projectType != null){
			buffer.append(" and role.projectType =:projectType ");
		}
		return buffer.toString();
	}

	@Override
	public Query prepareParams(Query query) {
		if(StringUtils.isNotBlank(templateId)){
			query.setString("templateId", templateId);
		}
		if(StringUtils.isNotBlank(likename)){
			query.setString("likename", "%"+likename+"%");
		}
		if(gradeType != null){
			query.setInteger("gradeType", gradeType);
		}
		if(projectType != null){
			query.setInteger("projectType", projectType);
		}
		return query;
	}
	@Override
	public String getSortname(){
		if(StringUtils.isBlank(super.getSortname())){
			super.setSortorder(ESortType.DESC.getCode());
			return "role.createTime";
		}
		return super.getSortname();
	}
	
	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getLikename() {
		return likename;
	}

	public void setLikename(String likename) {
		this.likename = likename;
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

	@Override
	public String getDataFilterSql() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
