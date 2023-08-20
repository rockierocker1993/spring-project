import id.rockierocker.kafka.KafkaProducer;
import org.junit.Test;
import rx.Observable;

public class ProductTest {

    @Test
    public void save(){
        KafkaProducer producer = new KafkaProducer("http://192.168.18.6:9092");
        producer.send("product","{\"id\":\"1234\",\"name\":\"test12\"}").subscribe();

    }
}
