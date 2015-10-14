/**
 * 
 */
package cn.fraudmetrix.kratos.batch.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.fraudmetrix.kratos.batch.dao.BlackListDao;
import cn.fraudmetrix.kratos.batch.entity.BlackListDO;
import cn.fraudmetrix.kratos.batch.service.BlackListService;

/**
 * @author zhengyong
 *
 */
@Service("blackListService")
@Transactional
public class BlackListServiceImpl implements BlackListService {

	private BlackListDao blackListDao;

	@Autowired
	public BlackListServiceImpl(BlackListDao blackListDao) {
		this.blackListDao = blackListDao;
	}

	@Override
	public BlackListDO insert(BlackListDO blackListDO) {
		return this.blackListDao.save(blackListDO);
	}

	@Override
	public List<BlackListDO> queryAll() {
		return (List<BlackListDO>) this.blackListDao.findAll();
	}
}
