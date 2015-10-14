package cn.yong.zheng.batch.config;


import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;

import cn.yong.zheng.batch.entity.BlackListDO;
import cn.yong.zheng.batch.util.BlackListDOItemProcessor;
import cn.yong.zheng.batch.util.BlackListFieldSetMapper;
import cn.yong.zheng.batch.util.BlackListItemWriter;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
	
	private static final Logger logger = LoggerFactory.getLogger(BatchConfiguration.class);
	
    /**
     * 读取外部文件方法
     * @return
     */
    @Bean
    public ItemReader<BlackListDO> reader() {
    	logger.info("=========reader========");
        FlatFileItemReader<BlackListDO> reader = new FlatFileItemReader<BlackListDO>();
        String fileURL = "csv/report.csv";
        reader.setResource(new ClassPathResource(fileURL));
        reader.setLineMapper(lineMapper());
        return reader;
    }
    
    /**
     * 读取文本行映射POJO
     * @return
     */
    @Bean
    public LineMapper<BlackListDO> lineMapper() {
    	logger.info("=========lineMapper========");
    	DefaultLineMapper<BlackListDO> lineMapper = new DefaultLineMapper<BlackListDO>();
    	DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
    	lineTokenizer.setDelimiter(",");
    	lineTokenizer.setStrict(false);
    	lineTokenizer.setNames(new String[] { "uuid","listNameUuid","appName","partnerCode"});
    	
    	BeanWrapperFieldSetMapper<BlackListDO> fieldSetMapper = new BeanWrapperFieldSetMapper<BlackListDO>();
    	fieldSetMapper.setTargetType(BlackListDO.class);
    	lineMapper.setLineTokenizer(lineTokenizer);
    	lineMapper.setFieldSetMapper(new BlackListFieldSetMapper());
    	return lineMapper;
    }

    /**
     * 处理过程
     * @return
     */
    @Bean
    public ItemProcessor<BlackListDO, BlackListDO> processor() {
    	logger.info("=========processor========");
        return new BlackListDOItemProcessor();
    }

    /**
     * 写出内容
     * @return
     */
    @Bean
    public ItemWriter<BlackListDO> writer() {
    	logger.info("=========writer========");
        return new BlackListItemWriter();
    }

    /**
     * 构建job
     * @param jobs
     * @param s1 
     * @param listener
     * @return
     */
    @Bean
    public Job importUserJob(JobBuilderFactory jobs, Step s1, JobExecutionListener listener,JobRepository jobRepository) {
    	logger.info("=========job========");
    	return jobs.get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .repository(jobRepository)
                .listener(listener)
                .flow(s1)
                .end()
                .build();
    }

    /**
     * 声明step
     * @param stepBuilderFactory 
     * @param reader 
     * @param writer
     * @param processor
     * @return
     */
    @Bean
    public Step step1(StepBuilderFactory stepBuilderFactory, ItemReader<BlackListDO> reader,
            ItemWriter<BlackListDO> writer, ItemProcessor<BlackListDO, BlackListDO> processor,PlatformTransactionManager transactionManager) {
    	logger.info("=========step========");
        return stepBuilderFactory.get("step1")
                .<BlackListDO, BlackListDO> chunk(10)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .faultTolerant()
        		.skipLimit(100)
        		.skip(Exception.class)
        		.transactionManager(transactionManager)
                .build();
    }
    
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
    
}
