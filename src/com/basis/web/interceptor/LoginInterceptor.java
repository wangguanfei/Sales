package com.basis.web.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.StrutsStatics;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.basis.core.common.Result;
import com.basis.web.common.BaseAction;

/**
 * 登录拦截器
 * @author wxliu
 *
 */
public class LoginInterceptor extends AbstractInterceptor 
{
	private static final long serialVersionUID = 4681650079453627277L;

	public String intercept(final ActionInvocation invocation) throws Exception {
		  ActionContext actionContext = invocation.getInvocationContext();
		  HttpServletRequest request= (HttpServletRequest) actionContext.get(StrutsStatics.HTTP_REQUEST);
		  BaseAction action=(BaseAction)invocation.getAction();
		 
		  if (action.getUser()==null){
			  if(request.getHeader("x-requested-with")!=null&&"XMLHttpRequest".equalsIgnoreCase(request.getHeader("x-requested-with"))){
				  //ajax请求
				  action.setResult(new Result<Object>(false,"message.login.timeout"));
				  return "json-result";
			  }else{
				  return "nologin";
			  }
		  }
		  return invocation.invoke();
	}

}
