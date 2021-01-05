package com.example.myfinalproject.data;

import com.example.myfinalproject.models.Transaction;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author Oksana
 */
public interface TransactionRepository extends CrudRepository<Transaction, Integer> {

   List<Transaction> findAllByAccountId(Integer accountId);

}
