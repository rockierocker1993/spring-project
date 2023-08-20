package id.rockierocker.kafka;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import rx.Observable;

import java.util.Properties;

public class KafkaProducer {

    private final String brokerAddress;

    private Properties properties;
    private org.apache.kafka.clients.producer.KafkaProducer<String, String> producer;

    public KafkaProducer(String brokerAddress) {
        this.brokerAddress = brokerAddress;
        setUpProperties();
        setUpProducer();
    }

    private void setUpProperties() {
        properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerAddress);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.ACKS_CONFIG, "0");
    }

    private void setUpProducer() {
        producer = new org.apache.kafka.clients.producer.KafkaProducer<>(properties);
    }

    public Observable<RecordMetadata> send(String topic, String message) {
        System.out.println("Succes send message : " + message + " to topic :" + topic);
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, message);

        return Observable.from(producer.send(record));
    }

    public Observable<RecordMetadata> send(String topic, String key, String message) {
        System.out.println("Succes send message : " + message + " to topic :" + topic);
        ProducerRecord<String, String> record = new ProducerRecord<>(topic,key, message);

        return Observable.from(producer.send(record));
    }

    public Observable<RecordMetadata> send(String topic, int partition, String key, String message) {
        System.out.println("Succes send message : " + message + " to topic :" + topic);
         ProducerRecord<String, String> record = new ProducerRecord<>(topic,partition,key, message);

        return Observable.from(producer.send(record));
    }

    public Observable<RecordMetadata> send(String topic, int partition, long timestamp, String key, String message) {
        System.out.println("Succes send message : " + message + " to topic :" + topic);
         ProducerRecord<String, String> record = new ProducerRecord<>(topic,partition,timestamp,key, message);

        return Observable.from(producer.send(record));
    }
  
}
