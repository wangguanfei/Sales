package com.basis.web.interceptor;

import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.StrutsStatics;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.basis.core.common.Result;
import com.basis.core.constants.EOperator;
import com.basis.core.domain.Employee;
import com.basis.web.common.Authority;
import com.basis.web.common.BaseAction;

/**
 * 用于拦截请求判断是否拥有权限的拦截器
 * 
 * @author wxliu
 */
public class AuthorityInterceptor extends AbstractInterceptor {
	private static final long serialVersionUID = 4681650079453627277L;

	public String intercept(final ActionInvocation invocation) throws Exception {
		ActionContext actionContext = invocation.getInvocationContext();
		HttpServletRequest request = (HttpServletRequest) actionContext.get(StrutsStatics.HTTP_REQUEST);
		Map session = ActionContext.getContext().getSession();
		// 如果是管理员就不用判断权限了
		if (((Employee) session.get(Employee.USER_SESSION_KEY)).isAdmin()) {
			return invocation.invoke();
		}

		String actionName = invocation.getProxy().getActionName();
		String methodName = invocation.getProxy().getMethod();
		Method currentMethod = invocation.getAction().getClass().getMethod(methodName, null);
		// 如果该请求方法是需要进行验证的则需执行以下逻辑
		if (currentMethod.isAnnotationPresent(Authority.class)) {
			// 获取权限校验的注解
			Authority currentAuthority = currentMethod.getAnnotation(Authority.class);
			// 获取当前请求的注解的author
			String currentAuthor = currentAuthority.author();
			String currentAuthorKey = StringUtils.isBlank(currentAuthor) ? actionName: currentAuthor;// 默认是action name
			// 获取当前请求需要的权限operator
			EOperator currentOperator = currentAuthority.operator();

			boolean hasAuthority = false;// 是否有权限
			Object session_authority = session.get(Employee.AUTHORITY_SESSION_KEY);
			if (session_authority != null) {
				Map<String, Integer> authMap = (Map<String, Integer>) session_authority;
				int authBit = authMap.get(currentAuthorKey) == null ? 0 : authMap.get(currentAuthorKey);
				if ((authBit & (1 << currentOperator.getCode())) != 0) {
					hasAuthority = true;
				}
			}
			// 没有权限
			if (!hasAuthority) {
				BaseAction action = (BaseAction) invocation.getAction();
				// ajax请求
				if (request.getHeader("x-requested-with") != null && "XMLHttpRequest".equalsIgnoreCase(request.getHeader("x-requested-with"))) {
					action.setResult(new Result<Object>(false,"message.nopermission"));
					return "json-result";
				} else {
					return "noauthority";
				}
			}
		}
		return invocation.invoke();
	}

}
