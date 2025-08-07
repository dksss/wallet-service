package org.jodyvole.walletservice.datasource.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "wallets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WalletEntity {

  @Id
  @Column(nullable = false, updatable = false)
  private UUID id;

  @Column(name = "balance", nullable = false)
  private BigDecimal balance;

}
