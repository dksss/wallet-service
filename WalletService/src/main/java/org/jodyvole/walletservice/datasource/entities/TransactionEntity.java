package org.jodyvole.walletservice.datasource.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jodyvole.walletservice.domain.model.OperationType;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "wallet_id", nullable = false)
  private WalletEntity wallet;

  @Enumerated(EnumType.STRING)
  @Column(name = "operation_type")
  private OperationType operationType;

}
