package com.basis.web.action.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.basis.core.exception.FileUtilException;
import com.basis.core.util.FileUtils;
import com.basis.core.util.PropertyUtils;
import com.basis.web.common.BaseAction;

/**
 * 文件上传action
 * 
 * @author wxliu
 * 
 */
public class DownloadAction extends BaseAction {
	private Logger logger = Logger.getLogger(DownloadAction.class);
	private static final String remoteRoot=PropertyUtils.getString("linux.fileupload.share.rootpath");
	//private static final String remoteRoot="e://";
	private String file;//请求下载的文件路径----相对路径
	
	private String fileName;
	
	private int isDelete = 0;
	
	private InputStream inputStream;
	
	/**
	 * 默认从本地服务器上下载
	 */
	public String execute(){
		return fromLocal();
	}
	
	/**
	 * 从本地服务器下载文件
	 * @throws FileUtilException 
	 */
	public String fromLocal(){
		String projectRoot=this.getServletRequest().getSession().getServletContext().getRealPath("/");
		if (isDelete == 1) {
			return exportDownLoad(projectRoot+file);//导出后下载
		}
		return download(projectRoot+file);
	}

	/**
	 * 从远程服务器--共享磁盘{RemoteRootDir} 下载文件
	 * @return String
	 */
	public String fromRemote(){
		return download(remoteRoot+file);
	}
	
	private String download(String filePath){
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("application/x-msdownload");
			File file = new File(filePath);
			if (file.exists()&&file.isFile()){
				String agent = request.getHeader("User-Agent");
				boolean isMSIE = (agent != null &&  (agent.indexOf("MSIE") != -1 || agent.indexOf("rv") != -1));
				if (isMSIE) {
					fileName = URLEncoder.encode(fileName, "UTF-8");
				} else {
					fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
				}
				response.setHeader("Content-Disposition", "attachment; filename=" + fileName );
				
				InputStream is = new FileInputStream(new File(filePath));
				OutputStream os = response.getOutputStream();
				
				int flag = 0;
				byte[] buff = new byte[1024*4];
				
				while((flag = is.read(buff, 0, buff.length))!=-1){
					os.write(buff, 0, flag);
				}
				os.flush();
				is.close();
				os.close();
			}else{
				addActionMessage("你访问的资源不存在!");
				return "message";
			}
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("下载文件出错!");
			return "message";
		}
		return null;
	}
	/**
	 * 导出后下载
	 * @param filePath
	 * @return
	 */
	private String exportDownLoad(String filePath){
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("application/x-msdownload");
			File file = new File(filePath);
			
			if (file.exists()&&file.isFile()){
				//fileName = new String(fileName.getBytes("gb2312"), "ISO8859-1");
				String agent = request.getHeader("User-Agent");
				boolean isMSIE = (agent != null && (agent.indexOf("MSIE") != -1 || agent.indexOf("rv") != -1));
				if (isMSIE) {
					fileName = URLEncoder.encode(fileName, "UTF-8");
				} else {
					fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
				}
				response.setHeader("Content-Disposition", "attachment; filename=" + fileName );
				
				InputStream is = new FileInputStream(new File(filePath));
				OutputStream os = response.getOutputStream();
				
				int flag = 0;
				byte[] buff = new byte[1024*4];
				
				while((flag = is.read(buff, 0, buff.length))!=-1){
					os.write(buff, 0, flag);
				}
				os.flush();
				is.close();
				os.close();
				 FileUtils.deleteFile(filePath);
			}else{
				addActionMessage("你访问的资源不存在!");
				return "message";
			}
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("下载文件出错!");
			return "message";
		}
		return null;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public int getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}

	
}
