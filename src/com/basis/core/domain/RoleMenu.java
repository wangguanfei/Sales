package com.basis.core.domain;

/**
 * RoleMenu entity. @author MyEclipse Persistence Tools
 */

public class RoleMenu implements java.io.Serializable {

	// Fields

	private String id;
	private String roleId;
	private String menuId;
	private Integer roleBit;
	private Menu menu;

	// Constructors

	/** default constructor */
	public RoleMenu() {
	}

	/** full constructor */
	public RoleMenu(String roleId, String menuId, Integer roleBit) {
		this.roleId = roleId;
		this.menuId = menuId;
		this.roleBit = roleBit;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public Integer getRoleBit() {
		return roleBit;
	}

	public void setRoleBit(Integer roleBit) {
		this.roleBit = roleBit;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	// Property accessors


}