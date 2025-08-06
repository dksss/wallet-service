package org.jodyvole.walletservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jodyvole.walletservice.domain.exceptions.InvalidAmountException;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Wallet {

  private UUID walletId;
  private Long balance;

  public void deposit(Long amount) {
    validateAmount(amount);
    balance += amount;
  }

  public void withdraw(Long amount) {
    validateAmount(amount);
    if (balance < amount) {
      throw new InvalidAmountException("not enough money to withdraw.");
    }
    balance -= amount;
  }

  private void validateAmount(Long amount) {
    if (amount == null || amount <= 0) {
      throw new InvalidAmountException("Amount must be greater than zero.");
    }
  }

}
