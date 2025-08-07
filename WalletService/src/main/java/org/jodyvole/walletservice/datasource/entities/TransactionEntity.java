package org.jodyvole.walletservice.datasource.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jodyvole.walletservice.domain.model.OperationType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionEntity {

  @Id
  private UUID id;

  @ManyToOne
  @JoinColumn(name = "wallet_id", nullable = false)
  private WalletEntity wallet;

  @Enumerated(EnumType.STRING)
  @Column(name = "operation_type", nullable = false, length = 15)
  private OperationType operationType;

  @Column(nullable = false)
  private BigDecimal amount;

  @Column(name = "created_at", nullable = false, updatable = false)
  private LocalDateTime createdAt;

  @PrePersist
  private void prePersist() {
    this.createdAt = LocalDateTime.now();
  }

}
