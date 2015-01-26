package com.basis.core.constants;

/**
 * 常量类
 */
public class Constant {
	/** 根绝对路径 * */
	public static String ROOTPATH = "";
    /**
     * 行业领域
     */
    public static final class industryField{
        //领域类型
        public static final Integer FILED_TYPE_ZD = 1;//重点领域
        
        public static final Integer FILED_TYPE_XDFW = 2;//现代服务业领域
       
    }
	    //项目状态
	    public static final Integer PROJECT_CB_STATUS_NEW = 1;//初报新建
	    
	    public static final Integer PROJECT_CB_STATUS_SUBMIT = 2;//初报提交
	    
	    public static final Integer PROJECT_CB_STATUS_RETURN = 3;//初审驳回
	    
	    public static final Integer PROJECT_SB_STATUS_NEW = 4;//申报新建
	    
	    public static final Integer PROJECT_SB_STATUS_SUBMIT = 5;//申报提交
	    
	    public static final Integer PROJECT_SB_STATUS_RETURN = 6;//申报驳回
	    
	    public static final Integer PROJECT_STATUS_PASS = 7;//审核通过
    /**
     * 高研班
     */
    public static final class gyb{
         //项目进展状态
         public static final Integer PROJECT_PRO_STATUS_NOT_BA = 0;//未进行通知/方案备案
		 
		 public static final Integer PROJECT_PRO_STATUS_IS_BA_NOT_SH = 1;//通知/方案已报送但未审核
		 
		 public static final Integer PROJECT_PRO_STATUS_IS_BA = 2;//已进行通知/方案备案
		 
		 public static final Integer PROJECT_PRO_STATUS_IS_JY = 3;//已结业(总结、决算表及人员名单已上报)
		 
		 //项目类型
		 public static final Integer PROJECT_TYPE_GYB = 1;//高研班
		 public static final Integer PROJECT_TYPE_JXRC = 2;//急需人才
		 public static final Integer PROJECT_TYPE_GWPX = 3;//岗位培训
		 public static final Integer PROJECT_TYPE_JD = 4;//基地
       
        //费用划拨状态
        public static final Integer PROJECT_FY_STATUS_NO = 0;//未划拨
        public static final Integer PROJECT_FY_STATUS_YES = 1;//以划拨
        
        //证书状态
        public static final Integer PROJECT_ZS_STATUS_NO = 0;//未生成
        public static final Integer PROJECT_ZS_STATUS_YES_NOT_FF = 1;//已生成但未发放
        public static final Integer PROJECT_ZS_STATUS_YES = 2;//已下发
        
        //开班通知
        public static final Integer PROJECT_TZ_STATUS_NOT = 0;//未审核
        public static final Integer PROJECT_TZ_STATUS_PASS = 1;//审核通过
        public static final Integer PROJECT_TZ_STATUS_NO = 2;//审核未通过
        
        
        //高研班证书流水号位数
        public static final Integer PROJECT_GYB_NUM = 5;
        //急需和岗位证书流水号位数
        public static final Integer PROJECT_JXGW_NUM = 7;
        
    }
    /**
     * 基地
     */
    public static final class jd{
    	 //报告计划状态
        public static final Integer REPORT_PLAN_STATUS_NO = 0;//未提交
        public static final Integer REPORT_PLAN_STATUS_NOT_SH = 1;//已提交未审核
        public static final Integer REPORT_PLAN_STATUS_PASS = 2;//审核通过
        public static final Integer REPORT_PLAN_STATUS_NOT_PASS = 3;//审核未通过
    }
}
