package com.sikku.kafka.orderProducer;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

public class TransactionalOrderProducer {

	public static void main(String[] args) {
		Properties props = new Properties();

		// Setting up the Kafka producer properties
		props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092"); // Setting the bootstrap servers
		props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
				"org.apache.kafka.common.serialization.StringSerializer"); // Setting the key serializer class
		props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
				"org.apache.kafka.common.serialization.IntegerSerializer"); // Setting the value serializer class

		props.setProperty(ProducerConfig.TRANSACTIONAL_ID_CONFIG, "Order-producer-1");
//		props.setProperty(ProducerConfig.MAX_BLOCK_MS_CONFIG, "1000");

		KafkaProducer<String, Integer> producer = new KafkaProducer<String, Integer>(props);
		producer.initTransactions();
		ProducerRecord<String, Integer> record1 = new ProducerRecord<>("OrderTopic", "Mac Book Pro", 1);
		ProducerRecord<String, Integer> record2 = new ProducerRecord<>("OrderTopic", "Mobile", 2);
		ProducerRecord<String, Integer> record3 = new ProducerRecord<>("OrderTopic", "Dell Laptop", 3);

		try {
			producer.beginTransaction();
			producer.send(record1);
			producer.send(record2);
			producer.send(record3);
			producer.commitTransaction();
		} catch (Exception e) {
			producer.abortTransaction();
			e.printStackTrace();
		} finally {
			producer.close();
		}
	}

}
