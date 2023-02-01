package org.id.bankingbatch.batch.reader;

import java.nio.file.Path;

import org.id.bankingbatch.entities.BankTransaction;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class BankTransactionReader {


	@Bean
	public FlatFileItemReader<BankTransaction> flatFileItemReader(@Value("C:\\Users\\ielmousa\\Desktop\\PrjPrsnls\\banking-batch\\src\\main\\resources\\data1.csv") String inputFile){
		FlatFileItemReader<BankTransaction> fileItemReader = new FlatFileItemReader<>();
		fileItemReader.setName("FFIR1");
		fileItemReader.setLinesToSkip(1);
		fileItemReader.setResource(new FileSystemResource(inputFile));
		fileItemReader.setLineMapper(lineMapper());
		System.out.println(fileItemReader.toString());
		return fileItemReader;
	}

	@Bean
	public LineMapper<BankTransaction> lineMapper() {
		// TODO Auto-generated method stub
		DefaultLineMapper<BankTransaction> lineMapper = new DefaultLineMapper<>();
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setDelimiter(",");
		lineTokenizer.setStrict(false);
		lineTokenizer.setNames("id","accountId","strTransactionDate","transactipnType","amount");
		
		lineMapper.setLineTokenizer(lineTokenizer);
		
		// spécifier l'objet cible dans lequel va stocker les données récupérés 
		BeanWrapperFieldSetMapper fieldSetMapper = new BeanWrapperFieldSetMapper<>();
		fieldSetMapper.setTargetType(BankTransaction.class);
		
		lineMapper.setFieldSetMapper(fieldSetMapper);
		
		return lineMapper;
	}
	
}
