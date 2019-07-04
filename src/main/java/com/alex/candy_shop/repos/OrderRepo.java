package com.alex.candy_shop.repos;

import com.alex.candy_shop.entities.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends CrudRepository<Order, Integer> {
}
