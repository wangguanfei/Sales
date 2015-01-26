/**   
* <desc> </desc>
* @author wxliu
* @date 2013-5-10
* @version V1.0   
*/
package com.basis.core.dto;

/**   
 * <desc>rest返回信息基类 </desc>
 * @author wxliu
 * @date 2013-5-10
 * @version V1.0   
 */
public class BaseResp {
	private boolean rlt;//结果状态
	private String msg;//结果信息
	
	
	public BaseResp() {
		this.rlt=true;
		this.msg="ok";
	}
	public boolean isRlt() {
		return rlt;
	}
	public void setRlt(boolean rlt) {
		this.rlt = rlt;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
