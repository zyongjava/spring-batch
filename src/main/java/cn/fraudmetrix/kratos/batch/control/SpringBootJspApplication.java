package cn.fraudmetrix.kratos.batch.control;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("cn.fraudmetrix.kratos.batch.dao")
@ComponentScan("cn.fraudmetrix.kratos.batch")
@EntityScan("cn.fraudmetrix.kratos.batch.entity")
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
