package com.sikku.kafka.orderProducer.simpleProducer;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import com.sikku.kafka.orderProducer.OrderCallBack;

public class SimpleOrderProducer {

	public static void main(String[] args) {
		Properties props = new Properties();

		// Setting up the Kafka producer properties
		props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092"); // Setting the bootstrap servers
		props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
				"org.apache.kafka.common.serialization.StringSerializer"); // Setting the key serializer class
		props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
				"org.apache.kafka.common.serialization.IntegerSerializer"); // Setting the value serializer class		
		
		KafkaProducer<String, Integer> producer = new KafkaProducer<String, Integer>(props);

		try {
			ProducerRecord<String, Integer> record = new ProducerRecord<>("SimpleConsumerTopic", "Mac Book Pro", 1);
			producer.send(record, new OrderCallBack());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			producer.close();
		}
	}

}
