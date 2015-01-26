package com.basis.web.interceptor;

import java.lang.reflect.Field;
import java.util.Map;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.basis.core.common.Condition;
import com.basis.core.domain.Employee;
import com.basis.core.domain.Role;

/**
 * @author wxliu
 */
@SuppressWarnings("serial")
public class DataFilterInterceptor extends AbstractInterceptor {

	public String intercept(final ActionInvocation invocation) throws Exception {
		Object action=invocation.getProxy().getAction();
		Field[] fields=action.getClass().getDeclaredFields();
		Map session=ActionContext.getContext().getSession();
		Employee employee=(Employee)session.get(Employee.USER_SESSION_KEY);
		for(Field field:fields){
			try{
				field.setAccessible(true);
				if(Condition.class.isAssignableFrom(field.getType())){
					Condition condition=(Condition)field.get(action);
					if(condition==null){
						field.set(action, field.getType().newInstance());
						condition=(Condition)field.get(action);
					}
					if(employee.isAdmin()){//如果是管理员就不做数据权限控制
				    	 condition.setDataFilter(false);
				    }
				}
			}catch (Exception e) {
				e.printStackTrace();
			}finally{
				field.setAccessible(false);
			}
		}
        return invocation.invoke();
	}

}
