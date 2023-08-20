package id.rockierocker.kafka;

public class ExampleMain {

    private final KafkaProducer producer;
    private static final String bootstrapAddress = "http://192.168.18.6:9092";
    private static final String topic = "product";
    private static final String group = "product";
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here
        
        ExampleMain apps =new ExampleMain();

        KafkaConsumerSettingsBean consumerSettingsBean = new KafkaConsumerSettingsBean();
        consumerSettingsBean.setBrokerAddress(bootstrapAddress);
        consumerSettingsBean.setGroupId(group);
        consumerSettingsBean.setTopic(topic);

        //KafkaConsumer kafkaConsumer = new KafkaConsumer(bootstrapAddress);
        apps.sendMessage(topic,"{\"id\":\"1234\",\"name\":\"test\"}");
        //kafkaConsumer.start((message) -> apps.printMessage((String) message),consumerSettingsBean,topic);

    }
      public ExampleMain() throws InterruptedException {
        this(bootstrapAddress, topic, topic);
             
    }

    public ExampleMain(String brokerAddress, String consumerTopic, String producerTopic) throws InterruptedException {
       
        producer = new KafkaProducer(brokerAddress);
       
    }

    public void sendMessage(String topic,String message) {
        producer.send(topic,message);
        System.out.println("terkirim");
    }
    
    private String printMessage(String pesan){
         System.out.println("writing" +pesan);
         return null;
     }

}
