package com.example.myfinalproject.data;

import com.example.myfinalproject.models.Hotel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Oksana
 */
@Repository
public interface HotelRepository extends CrudRepository<Hotel, Integer> {

   Hotel findByName(String name);
}
