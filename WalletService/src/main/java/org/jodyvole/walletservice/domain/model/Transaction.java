package org.jodyvole.walletservice.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record Transaction(
        UUID id,
        UUID walletId,
        OperationType operationType,
        BigDecimal amount,
        LocalDateTime createdAt
) {}
