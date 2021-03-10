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

        // 在1、换组之后；2、消费者挂掉之后，以至于以前的数据被删除，设置下面的属性才可以从目前主题中，从最早的信息开始拉取。
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earlist");

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

        /**
         * 消费者保存offest读取问题，手动提交offest相关。
         */
        // 当把自动提交offest属性关闭之后【properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,false)】，只要没有提交offest，每次打开该消费者组进行消费时，都是从上一次提交的offest位置开始读取
        // 比如，上一次提交的offest为90，这一次生产者程序KafkaRpoducerDemo01.java运行了一次，那么消费者控制台会打印90-100，这时，如果不手动提交offest到外部集群的话，在关闭该消费者之后，重新打开，仍然是从90开始消费，打印出来90-100
        // 然后，只要消费者不关闭，虽然已经读取到的90-100中的100位置上的offest没有提交，但是内存中已经记下了当前拉取到的offest，每次轮循拉去的时候都是从内存中记下的100开始拉取。
        // 否则从90开始拉取的话就不是从内存中找到90，而是去外部集群中查找offest，这样会导致速度变得很慢。

        /**
         * 手动提交offest：同步提交(commitSync),异步提交(commitAsync)
         */
        //
    }
}
