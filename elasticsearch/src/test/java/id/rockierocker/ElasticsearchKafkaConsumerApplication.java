package id.rockierocker;

import id.rockierocker.elasticsearch.product.entity.Product;
import id.rockierocker.elasticsearch.product.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = id.rockierocker.elasticsearch.ElasticsearchKafkaConsumerApplication.class)
public class ElasticsearchKafkaConsumerApplication {

    @Autowired
    ProductService productRepository;

    @Test
    public void contextLoad(){
        System.out.println("test");
        List<Product> productList = productRepository.findAll();
        System.out.println(productList);
    }

}
