package org.jodyvole.walletservice.web.controller.advice;

import org.jodyvole.walletservice.domain.exceptions.InvalidAmountException;
import org.jodyvole.walletservice.domain.exceptions.InvalidOperationTypeException;
import org.jodyvole.walletservice.domain.exceptions.InvalidWalletException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ControllerExceptionHandler {

  @ExceptionHandler(InvalidAmountException.class)
  public ResponseEntity<String> exceptionInvalidAmountHandler(InvalidAmountException e) {
    return ResponseEntity.badRequest().body(e.getMessage());
  }

  @ExceptionHandler(InvalidWalletException.class)
  public ResponseEntity<String> exceptionInvalidWalletHandler(InvalidWalletException e) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
  }

  @ExceptionHandler(InvalidOperationTypeException.class)
  public ResponseEntity<String> exceptionInvalidOperationTypeHandler(InvalidOperationTypeException e) {
    return ResponseEntity.badRequest().body(e.getMessage());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, String>> exceptionArgumentNotValidHandler(MethodArgumentNotValidException e) {
    Map<String, String> errors = new HashMap<>();
    for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
      errors.put(fieldError.getField(), fieldError.getDefaultMessage());
    }
    return ResponseEntity.badRequest().body(errors);
  }
}
