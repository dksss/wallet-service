package org.jodyvole.walletservice.web.mappers;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jodyvole.walletservice.domain.model.Transaction;
import org.jodyvole.walletservice.web.dto.TransactionDto;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TransactionWebMapper {

  public static TransactionDto toWeb(Transaction transaction) {
    return new TransactionDto(transaction.id(), transaction.walletId(), transaction.operationType().name(),
            transaction.amount(), transaction.createdAt());
  }

}
