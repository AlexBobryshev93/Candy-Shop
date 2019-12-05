package com.alex.candy_shop.repos;

import com.alex.candy_shop.entities.Order;
import com.alex.candy_shop.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends CrudRepository<Order, Integer> {
    List<Order> findAllByUser(User user);
}
