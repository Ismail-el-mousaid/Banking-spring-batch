package org.id.bankingbatch.batch.processor;

import java.text.SimpleDateFormat;

import org.id.bankingbatch.entities.BankTransaction;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import lombok.Getter;

//@Component  // pour qu'elle soit un composant spring (instanciable)
public class BankTransactionAnalyticsProcessor implements ItemProcessor<BankTransaction, BankTransaction>{
	
	@Getter private double totalDebit;
	@Getter private double totalCredit;

	@Override
	public BankTransaction process(BankTransaction bankTransaction) throws Exception {
		// TODO Auto-generated method stub
		if (bankTransaction.getTransactionType().equals("D"))
			totalDebit += bankTransaction.getAmount();
		else if (bankTransaction.getTransactionType().equals("C"))
			totalCredit += bankTransaction.getAmount();
		return bankTransaction;
	}
	
}
