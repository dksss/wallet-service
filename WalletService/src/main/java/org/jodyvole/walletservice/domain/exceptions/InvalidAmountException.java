package org.jodyvole.walletservice.domain.exceptions;

public class InvalidAmountException extends RuntimeException {
  public InvalidAmountException(String message) {
    super(message);
  }
}
