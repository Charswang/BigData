package Partitioner;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

/**
 * 实现Partitioner接口，实现自定义分区。
 */
public class CustomPartitioner implements Partitioner {
    public int partition(String s, Object o, byte[] bytes, Object o1, byte[] bytes1, Cluster cluster) {
        return 0; // 这里进行相应的hash等其他操作来自定义分区。
    }

    public void close() {

    }

    public void configure(Map<String, ?> map) {

    }
}
