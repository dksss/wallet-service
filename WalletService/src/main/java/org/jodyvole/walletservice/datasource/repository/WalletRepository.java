package org.jodyvole.walletservice.datasource.repository;

import org.jodyvole.walletservice.datasource.entities.WalletEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends CrudRepository<WalletEntity, Long> {
}
