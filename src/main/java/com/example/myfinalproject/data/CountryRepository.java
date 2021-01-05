package com.example.myfinalproject.data;

import com.example.myfinalproject.models.Country;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Oksana
 */
@Repository
public interface CountryRepository extends CrudRepository<Country, Integer> {
   Country findByName(String name);

}
