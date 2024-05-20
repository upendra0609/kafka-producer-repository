package com.sikku.kafka.orderProducer;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

public class OrderProducer {

	public static void main(String[] args) {
		Properties props = new Properties();

		// Setting up the Kafka producer properties
		props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092"); // Setting the bootstrap servers
		props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
				"org.apache.kafka.common.serialization.StringSerializer"); // Setting the key serializer class
		props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
				"org.apache.kafka.common.serialization.IntegerSerializer"); // Setting the value serializer class
		props.setProperty(ProducerConfig.ACKS_CONFIG, "all"); // Setting the acks configuration
		props.setProperty(ProducerConfig.BUFFER_MEMORY_CONFIG, "343434344"); // Setting the buffer memory

		props.setProperty(ProducerConfig.COMPRESSION_TYPE_CONFIG, "snappy"); // Setting compression type to snappy

		props.setProperty(ProducerConfig.RETRIES_CONFIG, "2"); // Setting the number of retries
		props.setProperty(ProducerConfig.RETRY_BACKOFF_MS_CONFIG, "500"); // Setting the retry backoff in milliseconds

		props.setProperty(ProducerConfig.BATCH_SIZE_CONFIG, "10233343434"); // Setting the batch size
		props.setProperty(ProducerConfig.LINGER_MS_CONFIG, "500"); // Setting the linger time

		props.setProperty(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, "200"); // Setting the request timeout in
																			// milliseconds
		props.setProperty(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, "true");
		
		KafkaProducer<String, Integer> producer = new KafkaProducer<String, Integer>(props);

		try {
			ProducerRecord<String, Integer> record = new ProducerRecord<>("OrderTopic", "Mac Book Pro", 1);
			producer.send(record, new OrderCallBack());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			producer.close();
		}
	}	

}
