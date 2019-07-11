package com.alex.candy_shop.repos;

import com.alex.candy_shop.entities.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends CrudRepository<Product, Integer> {
    Product findFirstByName(String name);
}
