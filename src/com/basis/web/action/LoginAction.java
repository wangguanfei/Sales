package com.basis.web.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.basis.core.common.Result;
import com.basis.core.condition.MenuCondition;
import com.basis.core.domain.Employee;
import com.basis.core.domain.Menu;
import com.basis.core.domain.RoleMenu;
import com.basis.core.service.ILoginService;
import com.basis.core.service.IMenuService;
import com.basis.web.common.BaseAction;
import com.opensymphony.xwork2.ActionContext;
/**
 * 登录、退出
 * @author wxliu
 *
 */
public class LoginAction extends BaseAction{
	private ILoginService loginService;
	private IMenuService menuService;
	private Employee employee;
	private String verifyCode="";
	/**
	 * 跳转到登录页面
	 * @return
	 */
	public String index(){
		return "login";
	}
	/**
	 * 登录
	 * @return
	 */
	public String login(){
		try{
//			if(getUser()!=null){//如果已经登录了
//				employee=getUser();
//				return "index";
//			}
			if(employee==null){
				addActionError("用户名和密码不能为空");
			}else if(StringUtils.isBlank(employee.getName())){
				addActionError("用户名不能为空");
			}else if(StringUtils.isBlank(employee.getPassword())){
				addActionError("密码不能为空");
			}
			/*if ("".equals(verifyCode.trim())) {
				addActionError("验证码不能为空");
			}*/
			/*String kaptchaExpected = (String)ActionContext.getContext().getSession().get(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
			if (null == kaptchaExpected) {//session超时
				return "login";
			}
			if (!kaptchaExpected.toLowerCase().equals(verifyCode.trim().toLowerCase())) {
				addActionError("验证码不正确");
			}*/
			if(hasActionErrors()){
				return "login";
			}
			Result<Employee> re=loginService.queryEmployeeLogin(employee);
			if(re.isSuccess()){
				Map<String, Integer> authorityMap=new HashMap<String, Integer>();//权限 格式{$author:$bit}
				List<Menu> menuList=new ArrayList<Menu>();//菜单列表
				employee=(Employee)re.getData();//用户信息
				//非管理员用户
				if(!employee.isAdmin()){
					Set<RoleMenu> rmSet=employee.getRole().getRoleMenuSet();
					Iterator<RoleMenu> iterator=rmSet.iterator();
					while(iterator.hasNext()){
						RoleMenu roleMenu=iterator.next();
						Menu menu=roleMenu.getMenu();
						//封装权限 authority
						if(StringUtils.isNotBlank(menu.getAuthor())){
							authorityMap.put(menu.getAuthor(), roleMenu.getRoleBit());
						}
						//封装菜单列表
						menuList.add(menu);
					}
				}else{//管理员用户
					List<Menu> allMenus=menuService.queryMenuList(new MenuCondition());
					for(Menu menu:allMenus){
						//封装权限 authority
						if(StringUtils.isNotBlank(menu.getAuthor())){
							authorityMap.put(menu.getAuthor(),Menu.MAX_BIT);
						}
						//封装菜单列表
						menuList.add(menu);
					}
				}
				Collections.sort(menuList,new MenuComparator());//排序
				//将用户信息、权限信息、菜单信息 放到session里
				set(Employee.USER_SESSION_KEY, employee);
				set(Employee.AUTHORITY_SESSION_KEY, authorityMap);
				set(Employee.MENU_SESSION_KEY, menuList);
				
			}else{
				addActionError(getText(re.getCode()));
				return "login";
			}
			return "index";
		}catch(Exception e){
			e.printStackTrace();
			return ERROR;
		}
		
	}
	
	/**
	 * 退出
	 * @return
	 */
	public String logout(){
		//将用户信息、权限信息、菜单信息 移除session
		remove(Employee.USER_SESSION_KEY);
		remove(Employee.AUTHORITY_SESSION_KEY);
		remove(Employee.MENU_SESSION_KEY);
		return "login";
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public void setLoginService(ILoginService loginService) {
		this.loginService = loginService;
	}

	public void setMenuService(IMenuService menuService) {
		this.menuService = menuService;
	}
	
	public class MenuComparator implements Comparator<Menu>{

		public int compare(Menu o1, Menu o2) {
			return o1.getSort()-o2.getSort();
		}
		
	}

	public String getVerifyCode() {
		return verifyCode;
	}
	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
	
}
