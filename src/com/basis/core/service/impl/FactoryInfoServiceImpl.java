/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2015
 */

package com.basis.core.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;

import com.basis.core.common.Page;
import com.basis.core.common.Result;
import com.basis.core.condition.FactoryInfoCondition;
import com.basis.core.dao.IBaseDao;
import com.basis.core.domain.FactoryInfo;
import com.basis.core.service.IFactoryInfoService;
import com.basis.core.util.DateUtil;
import com.basis.core.util.ExcelWritePoiUtil;
import com.basis.core.util.StringUtil;

/**
 * 厂家信息业务处理
 * 
 * @author wgf
 * @version 1.0
 * @since 1.0
 */

public class FactoryInfoServiceImpl implements IFactoryInfoService{
	private final static Logger logger=Logger.getLogger(FactoryInfoServiceImpl.class);
	private IBaseDao dao;

	
	/**
	 * 保存 FactoryInfo
	 */
	public Result<String> addFactoryInfo(FactoryInfo factoryInfo) {
		Result<String> result=new Result<String>(true); 
			String id=dao.addObject(factoryInfo);
			result.setData(id);
		return result;
	}
	
	/**
	 * 删除 FactoryInfo
	 */
	public Result<String> removeFactoryInfo(FactoryInfo factoryInfo) {
		Result<String> result=new Result<String>(true); 
		dao.deleteObject(factoryInfo);
		return new Result(true);
	}
	
	/**
	 * 更新 FactoryInfo
	 */
	public  Result<String>  modifyFactoryInfo(FactoryInfo factoryInfo) {
		Result<String> result=new Result<String>(true); 
		this.dao.updateObject(factoryInfo);
		return new Result(true);
	}
	
	/**
	 * 查询列表
	 */

	public List queryFactoryInfoList(FactoryInfoCondition condition) {
		/**
		 * 添加自己的判断条件
		 */
		return dao.queryObjectList(condition);
	}
	
	/**
	 * 分页查询
	 */
	public Page queryFactoryInfoPage(FactoryInfoCondition condition){
		return dao.queryPage(condition);
	}
	
	/**
	 * 根据ID查询
	 */
	public FactoryInfo queryFactoryInfoById(Long id){
		return dao.getObject(FactoryInfo.class, id);
	}	
	
	public String exportDatas(String rootpath,FactoryInfoCondition condition){
		ExcelWritePoiUtil writePoiUtil = new ExcelWritePoiUtil();
		HSSFSheet sheet = writePoiUtil.createSheet();
		String[] headers = {
				"id",
				"名称",
				"电话",
				"地址",
				"备注"
		};
		for(int i=0;i<headers.length;i++){
			sheet.setColumnWidth(i,3000);
		}
		// 设置头样式
		HSSFCellStyle headerStyle = writePoiUtil.createStyle();
		headerStyle.setWrapText(true);//自动换行
		headerStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
		// 输出头
		writePoiUtil.addRow(
				sheet, sheet.getFirstRowNum(), headers, headerStyle);
		condition.setPageSize(100);
		
		Page<FactoryInfo> page = this.queryFactoryInfoPage(condition);
		
		int index = 0;
		while(index < page.getRecordSize()){
			if(null==page.getData() || page.getData().isEmpty()){
				break;
			}
			for(int i=0;i<page.getData().size();i++){
				FactoryInfo e = page.getData().get(i);
				String[] datas = new String[]{
						null==e.getId()?"":e.getId()+"",
						null==e.getName()?"":e.getName()+"",
						null==e.getPhone()?"":e.getPhone()+"",
						null==e.getAddress()?"":e.getAddress()+"",
						null==e.getRemain()?"":e.getRemain()
					};
				try{
					writePoiUtil.addRow(sheet,(index+1), datas, (HSSFCellStyle)null);
				}catch(Exception e1){
					continue;
				}
				index++;
			}
			condition.setPageNo(condition.getPageNo()+1);
			page = this.queryFactoryInfoPage(condition);
		}
		
		String result = StringUtil.parseToPath(rootpath+"/exam/"+DateUtil.format(new Date(), "yyyy-MM-dd")+"/"+UUID.randomUUID().toString());
		try {
			writePoiUtil.saveExcel(result);
		} catch (Exception e) {
			//throw new ServiceException(e);
		}
		return result;
	}
	
	/**
	 * get set  方法
	 */
	public IBaseDao getDao() {
		return dao;
	}

	public void setDao(IBaseDao dao) {
		this.dao = dao;
	}
}	
