package org.jodyvole.walletservice.domain.service;

import lombok.RequiredArgsConstructor;
import org.jodyvole.walletservice.datasource.mappers.TransactionMapper;
import org.jodyvole.walletservice.datasource.mappers.WalletMapper;
import org.jodyvole.walletservice.datasource.repository.TransactionsRepository;
import org.jodyvole.walletservice.datasource.repository.WalletRepository;
import org.jodyvole.walletservice.domain.exceptions.InvalidWalletException;
import org.jodyvole.walletservice.domain.model.OperationType;
import org.jodyvole.walletservice.domain.model.Transaction;
import org.jodyvole.walletservice.domain.model.Wallet;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {

  private final WalletRepository walletRepository;
  private final TransactionsRepository transactionsRepository;

  @Override
  public Wallet create() {
    Wallet wallet = new Wallet();
    walletRepository.save(WalletMapper.toDatasource(wallet));
    return wallet;
  }

  @Override
  @Transactional
  public Transaction processOperation(UUID walletId, OperationType operationType, Long amount) {
    return null;
  }

  @Override
  public BigDecimal getBalance(UUID walletId) {
    Wallet wallet = findWallet(walletId);
    return wallet.getBalance();
  }

  @Override
  public List<Transaction> getAllOperations(UUID walletId) {
    return transactionsRepository.findAllOperationsByWallet(walletId).stream()
            .map(TransactionMapper::toDomain)
            .collect(Collectors.toList());
  }

  @Override
  public List<Wallet> getAllWallets() {
    return walletRepository.findAll().stream()
            .map(WalletMapper::toDomain)
            .collect(Collectors.toList());
  }

  private Wallet findWallet(UUID walletId) {
    return walletRepository.findById(walletId)
            .map(WalletMapper::toDomain)
            .orElseThrow(() -> new InvalidWalletException("Wallet with this id does not exist: " + walletId));
  }

}
