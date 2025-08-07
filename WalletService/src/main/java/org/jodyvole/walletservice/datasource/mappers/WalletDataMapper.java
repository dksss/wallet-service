package org.jodyvole.walletservice.datasource.mappers;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jodyvole.walletservice.datasource.entities.WalletEntity;
import org.jodyvole.walletservice.domain.model.Wallet;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class WalletDataMapper {

  public static Wallet toDomain(WalletEntity walletEntity) {
    return new Wallet(walletEntity.getId(), walletEntity.getBalance());
  }

  public static WalletEntity toDatasource(Wallet wallet) {
    return new WalletEntity(wallet.getWalletId(), wallet.getBalance());
  }

}
