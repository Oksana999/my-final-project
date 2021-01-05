package com.example.myfinalproject.data;

import com.example.myfinalproject.models.Account;
import com.example.myfinalproject.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Oksana
 */
@Repository
public interface UserRepository extends CrudRepository <User, Integer> {

   User findByUsername (String userName);
   User findByAccount (Account account);


}
