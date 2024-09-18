package com.rest.rabbit.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.rest.rabbit.model.OrderStatus;

@Component
public class PurchaseOrderConsumer {
	@Value("${rest.rabbitmq.exchange}") String exchange;
	@Value("${rest.rabbitmq.queue}") String queue;
	@Value("${rest.rabbitmq.routhingkey}") String routhingkey;
	
	@RabbitListener(queues = "rest_queue")
	public void consumerMessageFromQueue(OrderStatus orderStatus) {
		System.out.println("Message Recived From Queue : " + orderStatus);
	}
}
