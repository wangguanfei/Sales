package com.basis.core.domain;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

/**
 * Employee entity. @author MyEclipse Persistence Tools
 */

public class Employee implements java.io.Serializable {
	public final static String INITPASSWORD="000000";//初始化密码
	public final static String USER_SESSION_KEY="employeeSession";//用户登录后用户信息在Session里的key值
	public final static String AUTHORITY_SESSION_KEY="wallet.session.authority";//用户登录后用户权限在Session里的key值
	public final static String MENU_SESSION_KEY="wallet.session.menu";//用户登录后菜单在Session里的key值
	// Fields

	private String id;
	private String name;
	private String password;
	private Integer state;
	private Integer sort;
	private String createrId;
	private Date createTime;
	private String modifyerId;
	private Date modifyTime;
	private String roleId;
	private Employee creater;
	private Employee modifyer;
	private Role role;
	private Integer isadmin;
	
	private String repassword;
	
	private Long orgId;  //组织机构id
	private String orgName; //组织机构名称
	private String linkman;// 联系人
	private String tel;//办公电话
	private String phone;//手机
	private String email;//邮箱
	private String fax;//传真

	// Constructors

	/** default constructor */
	public Employee() {
	}

	public Employee(String id, String name, String password,
			Integer state, Integer sort, String createrId, Date createTime,
			String modifyerId, Date modifyTime, String roleId) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.state = state;
		this.sort = sort;
		this.createrId = createrId;
		this.createTime = createTime;
		this.modifyerId = modifyerId;
		this.modifyTime = modifyTime;
		this.roleId = roleId;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRepassword() {
		return repassword;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Integer getIsadmin() {
		return isadmin;
	}

	public void setIsadmin(Integer isadmin) {
		this.isadmin = isadmin;
	}
	public boolean isAdmin(){
		return this.isadmin==1;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}
	
}