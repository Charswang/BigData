package Consumer;

import org.apache.kafka.clients.consumer.*;

import java.util.Arrays;
import java.util.Properties;

public class ConsumerDemo01 {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"master:9092");
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,true); // 开启自动提交
        properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG,"1000"); // 自动提交的延时,1000ms,自动提交offest,"消费了offest10，那么就要把这个10发送到某一个地方来维护offest吧"
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringDeserializer"); // key值反序列化
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringDeserializer"); // value值反序列化
        properties.put(ConsumerConfig.GROUP_ID_CONFIG,"testkafka"); // 消费者组

        // 创建消费者
        KafkaConsumer kafkaConsumer = new KafkaConsumer<String,String>(properties);

        // 订阅主题
        kafkaConsumer.subscribe(Arrays.asList("first","second"));

        // 为了保持可以一直拉取数据，所以一直轮循下去
        while(true){
            // 消费者拉取数据
            ConsumerRecords<String,String> poll = kafkaConsumer.poll(100); // 消费者每拉取一次数据，会等待一会，来避免在没有数据发来的情况下，不停的轮循去拉取数据耗费资源。
            for (ConsumerRecord<String, String> consumerRecord : poll) {
                System.out.println(consumerRecord.key() + "--" + consumerRecord.value());
            }
        }
    }
}
