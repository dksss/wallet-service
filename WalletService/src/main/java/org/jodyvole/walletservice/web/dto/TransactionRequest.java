package org.jodyvole.walletservice.web.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequest {

  @NotNull(message = "Wallet id must be provided")
  private UUID walletId;

  @NotNull
  @Pattern(regexp = "DEPOSIT|WITHDRAW", flags = Pattern.Flag.CASE_INSENSITIVE,
           message = "Operation type must be DEPOSIT or WITHDRAW")
  private String operationType;

  @NotNull(message = "Amount must be provided")
  @Positive(message = "Amount must be positive")
  private BigDecimal amount;

}
