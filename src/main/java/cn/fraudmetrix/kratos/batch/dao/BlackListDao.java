/**
 * 
 */
package cn.fraudmetrix.kratos.batch.dao;

import org.springframework.data.repository.CrudRepository;

import cn.fraudmetrix.kratos.batch.entity.BlackListDO;


/**
 * @author zhengyong
 *
 */
public interface BlackListDao extends CrudRepository<BlackListDO, Long>{

}
