package Producer;

import org.apache.kafka.clients.producer.*;

import java.util.Properties;

public class CallBackProducer {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"master:9092");
        properties.put(ProducerConfig.ACKS_CONFIG,"all"); // 一般这个可以不设，可以只设置这三个。
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);

        for (int i = 0;i < 10;i++){
            // 这个方法中，有很多种不同参数的组合，其中partition参数代表要指定分区，key参数代表可以以key值的哈希值来确定发送的分区。
            producer.send(new ProducerRecord<String, String>("first", "hello->" + i), new Callback() {
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    if (e==null){
                        // 返回-->主题+分区+offest值
                        System.out.println(recordMetadata.topic() + "-->" + recordMetadata.partition() + "-->" + recordMetadata.offset());
                    }
                }
            });
            // lambda表达式
            /*producer.send(new ProducerRecord<String, String>("first", "hello->" + i),(recordMetadata,e)->{
                if (e==null){
                    System.out.println(recordMetadata.topic() + "--" + recordMetadata.partition() + "--" + recordMetadata.offset());
                }
            });*/
        }
        producer.close();
    }
}
