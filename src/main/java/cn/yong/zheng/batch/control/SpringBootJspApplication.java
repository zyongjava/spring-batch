package cn.yong.zheng.batch.control;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("cn.yong.zheng.batch.dao")
@ComponentScan("cn.yong.zheng.batch")
@EntityScan("cn.yong.zheng.batch.entity")
@SpringBootApplication
public class SpringBootJspApplication extends SpringBootServletInitializer{

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SpringBootJspApplication.class);
	}
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(SpringBootJspApplication.class, args);
	}
}
