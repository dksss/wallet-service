package org.jodyvole.walletservice.domain.exceptions;

public class InvalidWalletException extends RuntimeException {
  public InvalidWalletException(String message) {
    super(message);
  }
}
