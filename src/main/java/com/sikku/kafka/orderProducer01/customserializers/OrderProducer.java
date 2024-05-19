package com.sikku.kafka.orderProducer01.customserializers;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import com.sikku.kafka.orderProducer01.customserializers.partitioners.VIPPartitioner;

public class OrderProducer {

	public static void main(String[] args) {
		Properties props = new Properties();
		props.setProperty("bootstrap.servers", "localhost:9092");
		props.setProperty("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.setProperty("value.serializer", "com.sikku.kafka.orderProducer01.customserializers.OrderSerializer");
		props.setProperty("partitioner.class", VIPPartitioner.class.getName());

		KafkaProducer<String, Order> producer = new KafkaProducer<String, Order>(props);

		Order order = new Order();
		order.setCustomerName("John");
		order.setProduct("Mac Book Pro");
		order.setQuantity(1);

		try {
			ProducerRecord<String, Order> record = new ProducerRecord<>("OrderPartitionedTopic", order.getCustomerName(), order);
			producer.send(record);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			producer.close();
		}
	}

}
