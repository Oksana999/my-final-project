package com.example.myfinalproject.service;

import com.example.myfinalproject.data.AccountRepository;
import com.example.myfinalproject.data.TransactionRepository;
import com.example.myfinalproject.models.Account;
import com.example.myfinalproject.models.Transaction;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * @author Oksana
 */
@Service
public class TransactionService {

   private final AccountRepository accountRepository;

   private final TransactionRepository transactionRepository;

   public TransactionService(AccountRepository accountRepository,
                             TransactionRepository transactionRepository) {
      this.accountRepository = accountRepository;
      this.transactionRepository = transactionRepository;
   }

   public List<Transaction> getByAccount(Integer accountId) {
      return transactionRepository.findAllByAccountId(accountId);
   }

   @Transactional
   public void saveTransaction(BigDecimal amount, boolean isWithdraw, Integer accountId) {
      Optional<Account> accountOptional = accountRepository.findById(accountId);
      if (accountOptional.isPresent()) {
         Transaction transaction = new Transaction();
         transaction.setAmount(amount);
         transaction.setDate(System.currentTimeMillis());
         Account account = accountOptional.get();
         transaction.setAccount(account);
         transaction.setWithdraw(isWithdraw);

         transactionRepository.save(transaction);

         BigDecimal accountAmount = account.getAmount();
         BigDecimal actualAmount;
         if (isWithdraw) {
            actualAmount = accountAmount.subtract(amount);
         } else {
            actualAmount = accountAmount.add(amount);
         }

         account.setAmount(actualAmount);
         accountRepository.save(account);
      }
   }
}
