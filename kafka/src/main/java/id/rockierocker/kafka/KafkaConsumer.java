package id.rockierocker.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Properties;
import java.util.function.Function;

public class KafkaConsumer {

	HashMap<String, org.apache.kafka.clients.consumer.KafkaConsumer<String, String>> consumerMap = new HashMap<String, org.apache.kafka.clients.consumer.KafkaConsumer<String, String>>();
    private final String brokerAddress;
	private Object _lock = new Object();

    public KafkaConsumer(String brokerAddress) {
        this.brokerAddress = brokerAddress;
    }

    public void start(Function<ConsumerRecord<String, String>, Object> function, KafkaConsumerSettingsBean settings, String topic) {
		org.apache.kafka.clients.consumer.KafkaConsumer<String, String> kafkaConsumer = getConsumer(settings, topic);
        kafkaConsumer.subscribe(Arrays.asList(topic));
		while (true) {
			ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(Duration.ofMillis(100));
			for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
				System.out.println("Partition: " + consumerRecord.partition() + ", Offset: " + consumerRecord.offset()
						+ ", Key: " + consumerRecord.key() + ", Value: " + consumerRecord.value());
				function.apply(consumerRecord);
			}
			kafkaConsumer.commitSync();
		}
    }

    private org.apache.kafka.clients.consumer.KafkaConsumer<String, String> getConsumer(KafkaConsumerSettingsBean settings, String topic) {
		if (consumerMap == null || consumerMap.get(topic) == null) {
			synchronized (_lock) {
				if (consumerMap == null || consumerMap.get(topic) == null) {
					org.apache.kafka.clients.consumer.KafkaConsumer<String, String> consumer = new org.apache.kafka.clients.consumer.KafkaConsumer<String, String>(consumerConfig(settings));
					consumerMap.put(topic, consumer);
				}
			}
		}
		return consumerMap.get(topic);
	}


    private Properties consumerConfig(KafkaConsumerSettingsBean settings) {
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerAddress);
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, settings.getGroupId());
        properties.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        return properties;
    }

}
