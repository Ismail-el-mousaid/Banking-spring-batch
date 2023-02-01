package org.id.bankingbatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.XADataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication // (exclude = {DataSourceAutoConfiguration.class, XADataSourceAutoConfiguration.class })
//@ComponentScan (basePackages = "org.id.bankingbatch")
//@EnableJpaRepositories("org.id.bankingbatch.repository")
//@EnableAutoConfiguration
public class BankingBatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankingBatchApplication.class, args);
	}

}
