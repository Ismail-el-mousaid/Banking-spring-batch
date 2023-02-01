package org.id.bankingbatch.batch.writer;

import java.util.List;

import org.id.bankingbatch.entities.BankTransaction;
import org.id.bankingbatch.repository.BankTransactionRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BankTransactionWriter implements ItemWriter<BankTransaction> {
	
	@Autowired
	private BankTransactionRepository bankTransactionRepository;

	@Override
	public void write(List<? extends BankTransaction> listBankTransactions) throws Exception {
		// TODO Auto-generated method stub
		bankTransactionRepository.saveAll(listBankTransactions);
	}
	

}
