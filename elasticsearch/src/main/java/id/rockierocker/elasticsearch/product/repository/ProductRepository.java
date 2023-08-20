package id.rockierocker.elasticsearch.product.repository;

import id.rockierocker.elasticsearch.product.entity.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends ElasticsearchRepository<Product, String> {

    List<Product> findAll();

}
