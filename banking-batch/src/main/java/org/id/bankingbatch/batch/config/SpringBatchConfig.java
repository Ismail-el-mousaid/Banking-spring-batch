package org.id.bankingbatch.batch.config;

import java.util.ArrayList;
import java.util.List;

import org.id.bankingbatch.batch.processor.BankTransactionAnalyticsProcessor;
import org.id.bankingbatch.batch.processor.BankTransactionProcessor;
import org.id.bankingbatch.entities.BankTransaction;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.CompositeItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/* Create Job */

@Configuration
@EnableBatchProcessing  // Au démarrage : créer un certain nbr d'objets ou des beans en mémoire prédifinit de spring batch (comme jobBuilderFactory et stepBuilderFactory)
public class SpringBatchConfig {
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	@Autowired
	private ItemReader<BankTransaction> bankTransactionItemReader;
	@Autowired
	private ItemWriter<BankTransaction> bankTransactionItemWriter;
//	@Autowired
//	private ItemProcessor<BankTransaction, BankTransaction> bankTransactionItemProcessor;
	
	@Bean
	public Job bankJob() {
		Step step1 = stepBuilderFactory.get("ETL-Transaction-File-Load")
				.<BankTransaction,BankTransaction>chunk(100)
				.reader(bankTransactionItemReader)
				.processor(compositeItemProcessor())
				.writer(bankTransactionItemWriter)
				.build();
		// retourner le job
		return jobBuilderFactory.get("bank-data-loader-job")
				.start(step1).build();
	}

	// Ce composite permet d'Enchainer les 2 processors qu'on a 
	@Bean
	ItemProcessor<BankTransaction,BankTransaction> compositeItemProcessor() {
		// TODO Auto-generated method stub
		List<ItemProcessor<BankTransaction, BankTransaction>> itemProcessors = new ArrayList<>();
		itemProcessors.add(itemProcessor1());
		itemProcessors.add(itemProcessor2());
		
		CompositeItemProcessor<BankTransaction, BankTransaction> compositeItemProcessor = new CompositeItemProcessor<>();
		compositeItemProcessor.setDelegates(itemProcessors);
		
		return compositeItemProcessor;
	}
	
	// Autre manière d'instancier un objet (m chose que @Component)
	@Bean
	BankTransactionProcessor itemProcessor1(){
		return new BankTransactionProcessor();
	}
	
	@Bean
	BankTransactionAnalyticsProcessor itemProcessor2(){
		return new BankTransactionAnalyticsProcessor();
	}
	
	
}
