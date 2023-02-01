package org.id.bankingbatch.repository;

import org.id.bankingbatch.entities.BankTransaction;
import org.springframework.core.annotation.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankTransactionRepository extends JpaRepository<BankTransaction, Long>{

}
