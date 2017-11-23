package com.jimxu.boot.kafka;

import org.springframework.stereotype.Component;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaOperations;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.ProducerListener;

/**
 * @author xuzhijing
 * 2017年11月23日 下午3:12:34
 */

@Component
public class KafkaMsgProducer {
	
	@Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    public void send(String message) {
    	
        kafkaTemplate.send("jimxu-topic",message);
        kafkaTemplate.metrics();

        kafkaTemplate.execute(new KafkaOperations.ProducerCallback<String, String, Object>() {
            @Override
            public Object doInKafka(Producer<String, String> producer) {
                //这里可以编写kafka原生的api操作
                return null;
            }
        });

        //消息发送的监听器，用于回调返回信息
        kafkaTemplate.setProducerListener(new ProducerListener<String, String>() {
            @Override
            public void onSuccess(String topic, Integer partition, String key, String value, RecordMetadata recordMetadata) {

            }

            @Override
            public void onError(String topic, Integer partition, String key, String value, Exception exception) {

            }

            @Override
            public boolean isInterestedInSuccess() {
                return false;
            }
        });
    }

}
