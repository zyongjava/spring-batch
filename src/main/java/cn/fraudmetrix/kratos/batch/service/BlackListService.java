/**
 * 
 */
package cn.fraudmetrix.kratos.batch.service;

import java.util.List;

import cn.fraudmetrix.kratos.batch.entity.BlackListDO;

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
