/**
 * 
 */
package com.basis.core.condition;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;

import com.basis.core.common.Condition;

/** 
 * 此类描述的是： 
 * @author: wxliu
 * @version: 2013-2-17 下午04:22:58 
 */
public class QueryUserDefinedCourseCondition  extends Condition{
	
	private String courseName;
	private String equalCourseName;
	private String teacherId;
	private Integer gradeGroupId;
	private Integer subjectId;
	private String teacherName;
	private Integer isDeleted ;
	private Integer cityId;
	
	@Override
	public String getInitialHql() {
		
		StringBuilder hql = new StringBuilder(200);
		hql.append(" from UserDefinedCourse udc where 1 = 1  ");
		if(StringUtils.isNotBlank(courseName)){
			hql.append(" and udc.courseName like  :courseName ");
		}
		if(StringUtils.isNotBlank(equalCourseName)){
			hql.append(" and udc.courseName = :courseEqualName");
		}
		if(StringUtils.isNotBlank(teacherId)){
			hql.append(" and udc.teacherId = :teacherId ");
		}
		if(StringUtils.isNotBlank(teacherName)){
			hql.append(" and udc.teacherName = :teacherName ");
		}
		if(subjectId != null ){
			hql.append(" and udc.subjectId = :subjectId ");
		}
		if(gradeGroupId != null ){
			hql.append(" and udc.gradeGroupId = :gradeGroupId ");
		}
		if(isDeleted != null){
			hql.append(" and udc.isDeleted = :isDeleted ");
		}
		if(cityId != null ){
			hql.append(" and udc.cityId = :cityId ");
		}
		return hql.toString();
	}
	
	@Override
	public Query prepareParams(Query query) {
		
		if(StringUtils.isNotBlank(courseName)){
			query.setParameter("courseName", "%" + courseName + "%");
		}
		if(StringUtils.isNotBlank(equalCourseName)){
			query.setParameter("courseEqualName", equalCourseName);
		}
		if(StringUtils.isNotBlank(teacherId)){
			query.setParameter("teacherId", teacherId);
		}
		if(StringUtils.isNotBlank(teacherName)){
			query.setParameter("teacherName", teacherName);
		}
		if(subjectId != null ){
			query.setParameter("subjectId", subjectId);
		}
		if(gradeGroupId != null ){
			query.setParameter("gradeGroupId", gradeGroupId);
		}
		if(isDeleted != null){
			query.setParameter("isDeleted", isDeleted);
		}
		
		if(cityId != null){
			query.setParameter("cityId", cityId);
		}
		return query;
	}
	
	@Override
	public String getDataFilterSql() {
		// TODO Auto-generated method stub
		return "";
	}

	public String getTeacherId() {
		return teacherId;
	}

	public Integer getGradeGroupId() {
		return gradeGroupId;
	}

	public Integer getSubjectId() {
		return subjectId;
	}

	public String getTeacherName() {
		return teacherName;
	}


	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

	public void setGradeGroupId(Integer gradeGroupId) {
		this.gradeGroupId = gradeGroupId;
	}

	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getCourseName() {
		return courseName;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getEqualCourseName() {
		return equalCourseName;
	}

	public void setEqualCourseName(String equalCourseName) {
		this.equalCourseName = equalCourseName;
	}
	
}
