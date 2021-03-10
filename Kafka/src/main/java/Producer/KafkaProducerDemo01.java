package Producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

// ctrl+p --> 查看参数, ctrl+alt+v --> 只写了new 对象【new Properties()】出来的话，使用快捷键，自动补全前面的语句。
// ctrl+alt+t快捷键对指定代码进行try catch finally的选择。

/**
 * 在集群中开启消费者控制台
 * bin/kafka-console-consumer.sh --bootstrap-server master:9092 --topic first
 * 执行代码
 */
public class KafkaProducerDemo01 {
    public static void main(String[] args) {
        // 创建kafka生产者的配置信息
        Properties properties = new Properties();


        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"master:9092"); // 指定连接的kafka集群
        properties.put(ProducerConfig.ACKS_CONFIG,"all"); // ack应答级别
        properties.put(ProducerConfig.LINGER_MS_CONFIG,1); // 等待时间
        properties.put(ProducerConfig.BATCH_SIZE_CONFIG,16384); // 批次大小 16K
        properties.put(ProducerConfig.RETRIES_CONFIG,3); // 重试次数
        properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG,33554432); // 缓冲区大小32M
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer"); // 指定key的序列化类
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer"); // 指定value的序列化类

        // 创建生产者对象
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);

        /**
         * 生产环境中，生产者肯定需要不停的发送数据，在发送数据的过程中又要加上producer.close()方法，所以可以按照下面的方式来编写代码
         * while (true){
         *             try {
         *                 for (int i = 0;i<10;i++){
         *                     // 生产环境中，new ProducerRecord<String, String>("first","message--"+i)里面的value，实际上是从别处获取出来的封装的对象，来进行传输。而不是像这样自己造--。
         *                     producer.send(new ProducerRecord<String, String>("first","message--"+i));
         *                 }
         *             } catch (Exception e) {
         *                 e.printStackTrace();
         *             } finally {
         *                 producer.close();
         *             }
         *         }
         */
        // 发送数据
        for (int i = 0;i<10;i++){
            // 生产环境中，new ProducerRecord<String, String>("first","message--"+i)里面的value，实际上是从别处获取出来的封装的对象，来进行传输。而不是像这样自己造--。
            producer.send(new ProducerRecord<String, String>("first","message--"+i));
        }

        // 关闭生产者对象，一定关闭。如果不关闭的话，发送数据的时间没有超过1ms，所以并不会进行消费。原因？？
        // 解释：这位网友提到如果我们的主线程立刻退出了，而后端的IOThread还没来得及从队列中获取消息并发送给broker，这就导致了消息丢失了。
        // 参考：“一次kafka producer没有close引发的思考”   https://blog.csdn.net/QYHuiiQ/article/details/88757209
        producer.close(); // 官网说要close防止资源泄漏，数据丢失
        // 这里的close方法，不只是关闭这里的资源，而且还会对拦截器等资源进行一个清理。所以如果这里没写close方法，而是使用线程睡了100ms的话，是不会调用拦截器的close方法的。
    }
}
