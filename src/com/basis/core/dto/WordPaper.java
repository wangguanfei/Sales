package com.basis.core.dto;

import org.apache.commons.lang.StringUtils;

import com.basis.core.service.paper.transfer.JacobHandler;

public class WordPaper {

	private String filename;
	
	private String filepath;
	
	private String file;
	
	private String newWordFilename;
	
	private String htmlFilename;
	
	private String htmlFilepath;

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}
	
	public String getHtmlFilename() {
		return htmlFilename;
	}

	public void setHtmlFilename(String htmlFilename) {
		this.htmlFilename = htmlFilename;
	}

	public String getHtmlFilepath() {
		return htmlFilepath;
	}

	public void setHtmlFilepath(String htmlFilepath) {
		this.htmlFilepath = htmlFilepath;
	}

	public String getNewWordFilename() {
		return newWordFilename;
	}

	public void setNewWordFilename(String newWordFilename) {
		this.newWordFilename = newWordFilename;
	}

	/**
	 * 设置新文件名
	 */
	public String getNewFilename(){
		if(StringUtils.isBlank(file)){
			return "";
		}
		if(StringUtils.isBlank(filepath)){
			return file;
		}
		String newWordFilename=file.substring(filepath.length());
		return newWordFilename.substring(0, newWordFilename.indexOf("."));
	}
	/**
	 * 获得转换url
	 * @param url
	 * @return
	 */
	public String getConvertUrl(String url){
		StringBuffer buffer=new StringBuffer(url);
		buffer.append("word2html/converter");
//		buffer.append("?");
//		buffer.append("path=").append(this.getFilepath());
//		buffer.append("&filename=").append(this.getNewWordFilename());
		return buffer.toString();
	}
	public static void main(String[] args){
		JacobHandler handler=new JacobHandler();
		handler.word2Html("D:\\workspace\\basis-web\\src\\main\\webapp\\upload\\0a9ea251-18b4-4faa-8c02-c9ee720d73ef.docx","D:\\workspace\\basis-web\\src\\main\\webapp\\paper\\html\\0a9ea251-18b4-4faa-8c02-c9ee720d73ef.html");
	}
}
