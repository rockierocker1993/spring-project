package id.rockierocker.elasticsearch.product.service;

import id.rockierocker.elasticsearch.product.entity.Product;

import java.util.List;

public interface ProductService {

    Product save(Product product);

    Product findById(String id);

    List<Product> findAll();
}
