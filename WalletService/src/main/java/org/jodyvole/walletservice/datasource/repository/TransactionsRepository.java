package org.jodyvole.walletservice.datasource.repository;

import org.jodyvole.walletservice.datasource.entities.TransactionEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionsRepository extends CrudRepository<TransactionEntity, UUID> {

  @Query("SELECT t " +
          "FROM TransactionEntity t " +
          "WHERE t.wallet.id = :walletId " +
          "ORDER BY t.createdAt DESC")
  List<TransactionEntity> findAllOperationsByWallet(@Param("walletId") UUID walletId);

}
