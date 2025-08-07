package org.jodyvole.walletservice.datasource.mappers;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jodyvole.walletservice.datasource.entities.TransactionEntity;
import org.jodyvole.walletservice.domain.model.Transaction;
import org.jodyvole.walletservice.domain.model.Wallet;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TransactionDataMapper {

  public static Transaction toDomain(TransactionEntity entity) {
    return new Transaction(
            entity.getId(),
            entity.getWallet().getId(),
            entity.getOperationType(),
            entity.getAmount(),
            entity.getCreatedAt());
  }

  public static TransactionEntity toDatasource(Transaction domain, Wallet wallet) {
    TransactionEntity entity = new TransactionEntity();
    entity.setId(domain.id());
    entity.setWallet(WalletDataMapper.toDatasource(wallet));
    entity.setOperationType(domain.operationType());
    entity.setAmount(domain.amount());

    return entity;
  }

}
