package com.jimxu.boot.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author xuzhijing
 * 2017年11月23日 下午3:31:24
 */

@Component
public class KafkaMsgConsumer {

	@KafkaListener(topics = {"jimxu-topic"})
    public void processMessage(String content) {
        System.out.println(content);
    }
}
