package org.jodyvole.walletservice.web.mappers;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jodyvole.walletservice.domain.model.Wallet;
import org.jodyvole.walletservice.web.dto.WalletDto;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class WalletWebMapper {

  public static WalletDto toWeb(Wallet wallet) {
    return new WalletDto(wallet.getWalletId(), wallet.getBalance());
  }

}
