package com.example.myfinalproject.data;

import com.example.myfinalproject.models.Account;
import com.example.myfinalproject.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Oksana
 */
@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {

   public Account findByUser(User user);

}
