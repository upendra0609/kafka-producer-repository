package com.sikku.kafka.orderProducer;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;

public class OrderCallBack implements Callback {

	@Override
	public void onCompletion(RecordMetadata metadata, Exception exception) {
		System.out.println(metadata.partition());
		System.out.println(metadata.offset());
		System.out.println("message sent successfully");
		if(exception!=null) {
			exception.printStackTrace();
		}
	}

}
