/** configuration in web.xml get rootpath
 *  <listener>
    	<listener-class>com.up72.listener.WebAppRootKeyListener</listener-class>
  	</listener>
 */
package com.basis.core.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;

import com.basis.core.constants.Constant;

/**
 * 系统初始化 1、设置服务器中当前WebRoot的物理路径 
 * 
 */

public class WebAppRootKeyListener extends HttpServlet implements
		ServletContextListener {

	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger
			.getLogger(WebAppRootKeyListener.class);

	public void contextInitialized(ServletContextEvent sce) {
		// 初始化当前WebRoot的物理路径
		initContentPath(sce);
	}

	/**
	 * 初始化当前WebRoot的物理路径
	 */
	private void initContentPath(ServletContextEvent sce) {
		String rootpath = sce.getServletContext().getRealPath("/");
		if (rootpath != null) {
			rootpath = rootpath.replaceAll("\\\\", "/");
		} else {
			rootpath = "/";
		}
		if (!rootpath.endsWith("/")) {
			rootpath = rootpath + "/";
		}
		Constant.ROOTPATH = rootpath;
		if (logger.isInfoEnabled()) {
			logger.info("InitContentPath:[ROOTPATH=" + Constant.ROOTPATH);
		}
	}

	public void contextDestroyed(ServletContextEvent sce) {

	}
}
