package com.basis.web.action.file;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;
import com.basis.core.common.Result;
import com.basis.core.util.FileUtils;
import com.basis.core.util.PropertyUtils;
import com.basis.web.common.BaseAction;

/**
 * 文件上传action
 * 
 * @author sunchuanbao
 * 
 */
public class UploadAction extends BaseAction {
	private Logger logger = Logger.getLogger(UploadAction.class);
	/**
	 * 默认上传到本地服务器上
	 */
	public String execute()throws ServletException, IOException{
		return toLocal();
	}
	
	/**
	 * 上传文件到本地服务器上 
	 * 默认上传到{project.rootpath}upload/目录下
	 */
	public String toLocal() throws ServletException, IOException {
		HttpServletRequest request=this.getServletRequest();
		String dir = StringUtils.isBlank(request.getParameter("dir")) ? "upload/" : request.getParameter("dir");//默认上传到{RootDir}/upload/目录下
		String relativeDir = getDirpath(dir);// 相对路径
		String absoluteDir = getDirpath(request.getSession().getServletContext().getRealPath(relativeDir));
		result=upload(absoluteDir,relativeDir,false);
		return "json-result";
	}

	/**
	 * 上传文件到远程服务器上--共享磁盘{RemoteRootDir}
	 * 默认上传到{remote.rootpath}upload/目录下
	 * @return
	 */
	public String toRemote() {
		String remoteRootDir = PropertyUtils.getString("linux.fileupload.share.rootpath");//远程文件服务器根目录
		HttpServletRequest request=this.getServletRequest();
		String dir = StringUtils.isBlank(request.getParameter("dir")) ? "upload/" : request.getParameter("dir");//默认上传到{RemoteRootDir}/upload/目录下
		String relativeDir = getDirpath(dir);// 相对路径
		String absoluteDir = remoteRootDir+relativeDir;
		result=upload(absoluteDir,relativeDir,true);
		return "json-result";
	}
	/**
	 * 将上传的文件保存到absoluteDir目录中
	 * @param absoluteDir 绝对路径
	 * @param relativeDir 相对路径
	 * 例如：local本地:绝对路径{project.rootpath}upload/course/<===>相对路径upload/course/  
	 * 		remote远程：绝对路径{remote.rootpath}upload/course/<===>相对路径upload/course/ 
	 * @param isRemote  是否保存到远程服务器上
	 * @return
	 */
	private Result<Map> upload(String absoluteDir,String relativeDir,boolean isRemote){
		HttpServletRequest request = this.getServletRequest();
		Result<Map> result = new Result<Map>(true);
		String filename = "";// 文件名称
		String userDefinedFilename = "";// 自定义文件名称
		String mime = "";// 文件类型后缀
		try {
			logger.info("upload start");
			logger.info(absoluteDir);
			File dirFile = new File(absoluteDir);// 文件夹
			if (!dirFile.isDirectory()) {
				dirFile.mkdirs();
				if(isRemote){
					FileUtils.modifyFileOwn(absoluteDir);
				}
			}
			dirFile.setWritable(true);
			
			if (request instanceof MultiPartRequestWrapper) {
				MultiPartRequestWrapper requestWrapper = (MultiPartRequestWrapper) request;
				File[] files = requestWrapper.getFiles("Filedata");
				String[] filenames = requestWrapper.getFileNames("Filedata");
				if (files != null && files.length > 0 && filenames != null
						&& filenames.length > 0) {
					File file = files[0];
					filename = filenames[0];
					int pos = filename.lastIndexOf(".");
					mime = filename.substring(pos + 1);
					userDefinedFilename = UUID.randomUUID().toString() + "."
							+ mime;
					FileUtils.copyFile(file, absoluteDir, userDefinedFilename);
					if(isRemote){
						FileUtils.modifyFileOwn(absoluteDir + userDefinedFilename);
					}
				}
			}
			Map<String, String> map = new HashMap<String, String>();
			map.put("filename", filename);
			map.put("file", relativeDir + userDefinedFilename);//相对路径
			map.put("filepath", relativeDir);//相对路径
			map.put("newfilename", userDefinedFilename);
			result = new Result(true, "message.success");
			result.setData(map);
			logger.info("upload success!");
		} catch (Exception e) {
			e.printStackTrace();
			result = new Result(false, "message.exception");
		}
		return result;
	}
	
	
	/**
	 * 封转目录 以/结尾
	 * 
	 * @param dir
	 * @return
	 */
	private String getDirpath(String dir) {
		if (!(dir.endsWith("/") || dir.endsWith("\\"))) {
			return dir + "/";
		}
		return dir;
	}

}
