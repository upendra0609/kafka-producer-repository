package com.sikku.kafka.avro.serializers;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import com.sikku.kafka.avro.Order;

import io.confluent.kafka.serializers.KafkaAvroSerializer;

public class OrderProducer {

	public static void main(String[] args) {
		Properties props = new Properties();
		props.setProperty("bootstrap.servers", "127.0.0.1:9092");
		props.setProperty("key.serializer", KafkaAvroSerializer.class.getName());
		props.setProperty("value.serializer", KafkaAvroSerializer.class.getName());
		props.setProperty("schema.registry.url","http://localhost:8081");

		KafkaProducer<String, Order> producer = new KafkaProducer<>(props);

		Order order = new Order("Sikku","Mac Book Pro",3);
		ProducerRecord<String, Order> record = new ProducerRecord<>("OrderAvroTopic", order.getCustomerName().toString(), order);

		try {			
			producer.send(record);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			producer.close();
		}
	}

}
