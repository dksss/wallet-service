package org.jodyvole.walletservice.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jodyvole.walletservice.domain.model.OperationType;
import org.jodyvole.walletservice.domain.model.Transaction;
import org.jodyvole.walletservice.domain.model.Wallet;
import org.jodyvole.walletservice.domain.service.WalletService;
import org.jodyvole.walletservice.web.dto.TransactionRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(WalletController.class)
class WalletControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  private WalletService walletService;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  @DisplayName("POST /wallet/create - should return created wallet")
  void createWalletTest() throws Exception {
    Wallet wallet = new Wallet();

    Mockito.when(walletService.create()).thenReturn(wallet);

    mockMvc.perform(post("/api/v1/wallet/create"))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id", is(wallet.getWalletId().toString())))
            .andExpect(jsonPath("$.balance", is(0)));
  }

  @Test
  @DisplayName("POST /wallet - should return created transaction")
  void processTransactionDepositTest() throws Exception {
    UUID walletId = UUID.randomUUID();
    TransactionRequest request = new TransactionRequest(walletId, "deposit", BigDecimal.TEN);
    Transaction transaction = new Transaction(UUID.randomUUID(), walletId,
            OperationType.DEPOSIT, BigDecimal.TEN, LocalDateTime.now());

    Mockito.when(walletService.processTransaction(Mockito.any(TransactionRequest.class))).thenReturn(transaction);

    mockMvc.perform(post("/api/v1/wallet")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id", is(notNullValue())))
            .andExpect(jsonPath("$.walletId", is(walletId.toString())))
            .andExpect(jsonPath("$.operationType", is("DEPOSIT")))
            .andExpect(jsonPath("$.amount", is(10)));
  }

  @Test
  @DisplayName("GET /wallet/{walletID} - should return wallet balance")
  void getBalanceTest() throws Exception {
    UUID walletId = UUID.randomUUID();

    Mockito.when(walletService.getBalance(walletId)).thenReturn(BigDecimal.valueOf(300));

    mockMvc.perform(get("/api/v1/wallets/" + walletId))
            .andExpect(status().isOk())
            .andExpect(content().string("300"));
  }

  @Test
  @DisplayName("GET /wallets/operations/{walletId} - should return list of transactions")
  void getOperationsTest() throws Exception {
    UUID walletId = UUID.randomUUID();
    List<Transaction> transactions = List.of(new Transaction(
            UUID.randomUUID(), walletId, OperationType.DEPOSIT, BigDecimal.valueOf(500), LocalDateTime.now()));

    Mockito.when(walletService.getAllOperations(walletId)).thenReturn(transactions);

    mockMvc.perform(get("/api/v1/wallets/operations/" + walletId))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(transactions.size())))
            .andExpect(jsonPath("$[0].operationType", is("DEPOSIT")));
  }

  @Test
  @DisplayName("GET /wallets/all - should return all wallets")
  void getAllWalletsTest() throws Exception {
    List<Wallet> wallets = List.of(
            new Wallet(UUID.randomUUID(), BigDecimal.valueOf(100)),
            new Wallet(UUID.randomUUID(), BigDecimal.valueOf(10)));

    Mockito.when(walletService.getAllWallets()).thenReturn(wallets);

    mockMvc.perform(get("/api/v1/wallets/all"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(wallets.size())));
  }


}