package id.rockierocker.elasticsearch.product.eventdriven;

import com.fasterxml.jackson.databind.ObjectMapper;
import id.rockierocker.elasticsearch.product.entity.Product;
import id.rockierocker.elasticsearch.product.service.ProductService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.AcknowledgingMessageListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Service
public class ProductEventDriven implements AcknowledgingMessageListener<String, String> {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    ProductService productService;

    @Override
    @KafkaListener(topics = "product", containerFactory = "kafkaListenerContainerFactory")
    public void onMessage(ConsumerRecord<String, String> record, Acknowledgment acknowledgment) {
        try {
            // Proses pesan
            System.out.println("Received message: " + record.value());
            Product product = objectMapper.readValue(record.value(), Product.class);
            productService.save(product);
            // Manually acknowledge the message
            acknowledgment.acknowledge();
        } catch (Exception e) {
            // Handle the error or log it
            System.err.println("Error processing message: " + e.getMessage());

            // You can choose to not acknowledge the message in case of error to prevent offset commit
        }
    }
}
