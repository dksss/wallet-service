package org.jodyvole.walletservice.datasource.repository;

import org.jodyvole.walletservice.datasource.entities.TransactionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionsRepository extends CrudRepository<TransactionEntity, Long> {
}
