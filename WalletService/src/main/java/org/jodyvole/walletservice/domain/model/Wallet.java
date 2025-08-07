package org.jodyvole.walletservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jodyvole.walletservice.domain.exceptions.InvalidAmountException;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class Wallet {

  private final UUID walletId;
  private BigDecimal balance;

  public Wallet() {
    this.walletId = UUID.randomUUID();
    this.balance = BigDecimal.ZERO;
  }

  public void deposit(BigDecimal amount) {
    validateAmount(amount);
    balance = balance.add(amount);
  }

  public void withdraw(BigDecimal amount) {
    validateAmount(amount);
    if (balance.subtract(amount).compareTo(BigDecimal.ZERO) < 0) {
      throw new InvalidAmountException("not enough money to withdraw.");
    }

    balance = balance.subtract(amount);
  }

  private void validateAmount(BigDecimal amount) {
    if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
      throw new InvalidAmountException("Amount must be greater than zero.");
    }
  }

}
