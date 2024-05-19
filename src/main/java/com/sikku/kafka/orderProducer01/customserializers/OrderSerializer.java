package com.sikku.kafka.orderProducer01.customserializers;

import org.apache.kafka.common.serialization.Serializer;

import com.fasterxml.jackson.databind.ObjectMapper;

public class OrderSerializer implements Serializer<Order> {

	@Override
	public byte[] serialize(String topic, Order data) {
		byte[] response = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			response = mapper.writeValueAsString(data).getBytes();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

}
