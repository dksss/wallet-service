package org.jodyvole.walletservice.domain.service;

import org.jodyvole.walletservice.domain.model.Transaction;
import org.jodyvole.walletservice.domain.model.Wallet;
import org.jodyvole.walletservice.web.dto.TransactionRequest;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface WalletService {

  Wallet create();

  Transaction processTransaction(TransactionRequest request);

  BigDecimal getBalance(UUID walletId);

  List<Transaction> getAllOperations(UUID walletId);

  List<Wallet> getAllWallets();

}
