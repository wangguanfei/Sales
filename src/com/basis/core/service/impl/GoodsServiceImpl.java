/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2014
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
import com.basis.core.condition.GoodsCondition;
import com.basis.core.dao.IBaseDao;
import com.basis.core.domain.Goods;
import com.basis.core.service.IGoodsService;
import com.basis.core.util.DateUtil;
import com.basis.core.util.ExcelWritePoiUtil;
import com.basis.core.util.StringUtil;

/**
 * 商品业务处理
 * 
 * @author wgf
 * @version 1.0
 * @since 1.0
 */

public class GoodsServiceImpl implements IGoodsService{
	private final static Logger logger=Logger.getLogger(GoodsServiceImpl.class);
	private IBaseDao dao;

	
	/**
	 * 保存 Goods
	 */
	public Result<String> addGoods(Goods goods) {
		Result<String> result=new Result<String>(true); 
			String id=dao.addObject(goods);
			result.setData(id);
		return result;
	}
	
	/**
	 * 删除 Goods
	 */
	public Result<String> removeGoods(Goods goods) {
		Result<String> result=new Result<String>(true); 
		dao.deleteObject(goods);
		return new Result(true);
	}
	
	/**
	 * 更新 Goods
	 */
	public  Result<String>  modifyGoods(Goods goods) {
		Result<String> result=new Result<String>(true); 
		this.dao.updateObject(goods);
		return new Result(true);
	}
	
	/**
	 * 查询列表
	 */

	public List queryGoodsList(GoodsCondition condition) {
		/**
		 * 添加自己的判断条件
		 */
		return dao.queryObjectList(condition);
	}
	
	/**
	 * 分页查询
	 */
	public Page queryGoodsPage(GoodsCondition condition){
		return dao.queryPage(condition);
	}
	
	/**
	 * 根据ID查询
	 */
	public Goods queryGoodsById(Long id){
		return dao.getObject(Goods.class, id);
	}	
	
	public String exportDatas(String rootpath,GoodsCondition condition){
		ExcelWritePoiUtil writePoiUtil = new ExcelWritePoiUtil();
		HSSFSheet sheet = writePoiUtil.createSheet();
		String[] headers = {
				"ID",
				"商品名称",
				"商品图片",
				"库存",
				"简介"
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
		
		Page<Goods> page = this.queryGoodsPage(condition);
		
		int index = 0;
		while(index < page.getRecordSize()){
			if(null==page.getData() || page.getData().isEmpty()){
				break;
			}
			for(int i=0;i<page.getData().size();i++){
				Goods e = page.getData().get(i);
				String[] datas = new String[]{
						null==e.getId()?"":e.getId()+"",
						null==e.getName()?"":e.getName()+"",
						null==e.getImg()?"":e.getImg()+"",
						null==e.getStock()?"":e.getStock()+"",
						null==e.getIntroduction()?"":e.getIntroduction()
					};
				try{
					writePoiUtil.addRow(sheet,(index+1), datas, (HSSFCellStyle)null);
				}catch(Exception e1){
					continue;
				}
				index++;
			}
			condition.setPageNo(condition.getPageNo()+1);
			page = this.queryGoodsPage(condition);
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

	@Override
	public List<Goods> getYujing() {
		String sql = "SELECT * FROM `zsgx_goods` WHERE stock < 10 ";
		return dao.getSession().createSQLQuery(sql).addEntity(Goods.class).list();
	}
}	
