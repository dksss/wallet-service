package org.jodyvole.walletservice.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.jodyvole.walletservice.domain.service.WalletService;
import org.jodyvole.walletservice.web.dto.TransactionDto;
import org.jodyvole.walletservice.web.dto.TransactionRequest;
import org.jodyvole.walletservice.web.dto.WalletDto;
import org.jodyvole.walletservice.web.mappers.TransactionWebMapper;
import org.jodyvole.walletservice.web.mappers.WalletWebMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class WalletController {

  private final WalletService walletService;

  @PostMapping("/wallet/create")
  public ResponseEntity<WalletDto> createWallet() {
    WalletDto wallet = WalletWebMapper.toWeb(walletService.create());
    return ResponseEntity.status(HttpStatus.CREATED).body(wallet);
  }

  @PostMapping("/wallet")
  public ResponseEntity<TransactionDto> processTransaction(@Valid @RequestBody TransactionRequest request) {
    TransactionDto transaction = TransactionWebMapper.toWeb(walletService.processTransaction(request));
    return ResponseEntity.status(HttpStatus.CREATED).body(transaction);
  }

  @GetMapping("/wallets/{walletUuid}")
  public ResponseEntity<BigDecimal> getBalance(@PathVariable UUID walletUuid ) {
    BigDecimal balance = walletService.getBalance(walletUuid);
    return ResponseEntity.ok(balance);
  }

  @GetMapping("/wallets/operations/{walletUuid}")
  public ResponseEntity<List<TransactionDto>> getOperations(@PathVariable UUID walletUuid) {
    List<TransactionDto> transactions = walletService.getAllOperations(walletUuid).stream()
            .map(TransactionWebMapper::toWeb)
            .toList();
    return ResponseEntity.ok(transactions);
  }

  @GetMapping("/wallets/all")
  public ResponseEntity<List<WalletDto>> getAllWallets() {
    List<WalletDto> wallets = walletService.getAllWallets().stream()
            .map(WalletWebMapper::toWeb)
            .toList();
    return ResponseEntity.ok(wallets);
  }

}
