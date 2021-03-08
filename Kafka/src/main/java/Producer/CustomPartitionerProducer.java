package Producer;

import Partitioner.CustomPartitioner;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * 添加自定义分区器
 * properties.put(ProducerConfig.PARTITIONER_CLASS_CONFIG,"Partitioner.CustomPartitioner.java");
 */
public class CustomPartitionerProducer {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"master:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        // 设置自定义分区,也叫添加分区器。
        properties.put(ProducerConfig.PARTITIONER_CLASS_CONFIG,"Partitioner.CustomPartitioner");

        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);

        producer.send(new ProducerRecord<String, String>("first","onekey","helloonekey"));

        // 如果想让异步发送变为同步发送，那么在这个send方法之后加上get()方法,起到阻塞作用，类似于多线程中的join
        // producer.send(new ProducerRecord<String, String>("first","onekey","helloonekey")).get(); // 要抛出异常。

        producer.close();
    }
}
