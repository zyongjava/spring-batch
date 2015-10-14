/**
 * 
 */
package cn.yong.zheng.batch.service;

import java.util.List;

import cn.yong.zheng.batch.entity.BlackListDO;

/**
 * @author zhengyong
 *
 */
public interface BlackListService {
	
	/**
	 * 保存黑名单数据
	 * @param blackListDO
	 * @return
	 */
	public BlackListDO insert(BlackListDO blackListDO);
	
	/**
	 * 
	 * @return
	 */
	public List<BlackListDO> queryAll();

}
