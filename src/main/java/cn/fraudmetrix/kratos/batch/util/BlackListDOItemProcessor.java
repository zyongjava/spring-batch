/**
 * 
 */
package cn.fraudmetrix.kratos.batch.util;

import java.sql.Timestamp;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import cn.fraudmetrix.kratos.batch.entity.BlackListDO;

/**
 * @author zhengyong
 *
 */
public class BlackListDOItemProcessor implements ItemProcessor<BlackListDO, BlackListDO> {

	private static final Logger logger = LoggerFactory.getLogger(BlackListDOItemProcessor.class);
	
	public BlackListDO process(BlackListDO blackListDO) throws Exception {
		logger.info("============批量自动导入黑名单数据process===============");
		blackListDO.setDescription("批量自动导入黑名单数据");
		blackListDO.setGmtCreate(new Timestamp(new Date().getTime()));
		return blackListDO;
	}

}
