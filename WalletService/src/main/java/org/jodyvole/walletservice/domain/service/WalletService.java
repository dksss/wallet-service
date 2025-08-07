package org.jodyvole.walletservice.domain.service;

import org.jodyvole.walletservice.domain.model.OperationType;
import org.jodyvole.walletservice.domain.model.Transaction;
import org.jodyvole.walletservice.domain.model.Wallet;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface WalletService {

  Wallet create();

  Transaction processOperation(UUID walletId, OperationType operationType, Long amount);

  BigDecimal getBalance(UUID walletId);

  List<Transaction> getAllOperations(UUID walletId);

  List<Wallet> getAllWallets();

}
