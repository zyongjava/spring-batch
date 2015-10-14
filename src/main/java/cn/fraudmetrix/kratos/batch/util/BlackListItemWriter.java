package cn.fraudmetrix.kratos.batch.util;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import cn.fraudmetrix.kratos.batch.entity.BlackListDO;
import cn.fraudmetrix.kratos.batch.service.BlackListService;

public class BlackListItemWriter implements ItemWriter<BlackListDO>{
	
	private static final Logger logger = LoggerFactory.getLogger(BlackListItemWriter.class);

	@Autowired
	private BlackListService blackListService;
	
	@Override
	public void write(List<? extends BlackListDO> blackList) throws Exception {
		logger.info("=========黑名单入库========");
		for(BlackListDO blackListDO : blackList){
			//jdbcTemplate.update( INSERT_BLACKLIST, blackListDO.getId(),blackListDO.getUuid(), blackListDO.getListNameUuid(),blackListDO.getAppName(),blackListDO.getPartnerCode());
			blackListService.insert(blackListDO);
		}
	}


}