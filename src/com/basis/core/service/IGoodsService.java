/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2014
 */

package com.basis.core.service;

import java.util.List;

import org.apache.log4j.Logger;
import com.basis.core.common.Page;
import com.basis.core.domain.Goods;
import com.basis.core.common.Result;

import com.basis.core.condition.GoodsCondition;
/**
 * 商品业务处理
 * 
 * @author wgf
 * @version 1.0
 * @since 1.0
 */

public interface IGoodsService {
	public static Logger logger = Logger.getLogger(IGoodsService.class);
		
	/**
	 * 保存 Goods
	 */
	public Result addGoods(Goods goods);
	
	/**
	 * 删除 Goods
	 */
	public Result removeGoods(Goods goods);
	
	/**
	 * 更新 Goods
	 */
	public Result modifyGoods(Goods goods);
	/**
	 * 查询列表
	 */
	public List queryGoodsList(GoodsCondition condition);
	/**
	 * 分页查询
	 */
	public Page queryGoodsPage(GoodsCondition condition);
	
	/**
	 * 根据ID 查询
	 */
	public Goods queryGoodsById(Long id);	
	/**
	 * 数据导出
	 * @param condition 查询条件
	 * @return 导出的excel路径
	 */
	public String exportDatas(String rootpath,GoodsCondition condition);
	
	public List<Goods> getYujing();
}
