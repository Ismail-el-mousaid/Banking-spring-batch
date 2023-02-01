package org.id.bankingbatch.batch.processor;

import java.text.SimpleDateFormat;

import org.id.bankingbatch.entities.BankTransaction;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

//@Component  // pour qu'elle soit un composant spring
public class BankTransactionProcessor implements ItemProcessor<BankTransaction, BankTransaction>{
	
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy-HH:mm");

	@Override
	public BankTransaction process(BankTransaction bankTransaction) throws Exception {
		// TODO Auto-generated method stub
		bankTransaction.setTransactionDate(dateFormat.parse(bankTransaction.getStrTransactionDate()));
		return bankTransaction;
	}
	
}
