package com.basis.core.domain;

/**
 * RoletemplateMenu entity. @author MyEclipse Persistence Tools
 */

public class RoletemplateMenu implements java.io.Serializable {

	// Fields

	private String id;
	private String templateId;
	private String menuId;
	private Integer roleBit;
	private Menu menu;

	// Constructors

	/** default constructor */
	public RoletemplateMenu() {
	}

	/** full constructor */
	public RoletemplateMenu(String templateId, String menuId, Integer roleBit) {
		this.templateId = templateId;
		this.menuId = menuId;
		this.roleBit = roleBit;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTemplateId() {
		return this.templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getMenuId() {
		return this.menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public Integer getRoleBit() {
		return this.roleBit;
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
	
	

}