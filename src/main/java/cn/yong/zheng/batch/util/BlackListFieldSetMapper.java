package cn.yong.zheng.batch.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import cn.yong.zheng.batch.entity.BlackListDO;

public class BlackListFieldSetMapper implements FieldSetMapper<BlackListDO> {
	
	private static final Logger logger = LoggerFactory.getLogger(BlackListFieldSetMapper.class);
	
	@Override
	public BlackListDO mapFieldSet(FieldSet fieldSet) throws BindException {
		
		logger.info("============BlackListFieldSetMapper转换POJO=============");
		BlackListDO BlackListDO = new BlackListDO();
		BlackListDO.setUuid(fieldSet.readString("uuid"));
		BlackListDO.setListNameUuid(fieldSet.readString("listNameUuid"));
		BlackListDO.setAppName(fieldSet.readString("appName"));
		BlackListDO.setPartnerCode(fieldSet.readString("partnerCode"));
		return BlackListDO;
	}
}
