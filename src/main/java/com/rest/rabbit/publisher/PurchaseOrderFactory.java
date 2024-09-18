package com.rest.rabbit.publisher;

import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.rabbit.model.OrderStatus;
import com.rest.rabbit.model.PurchaseOrder;


@RestController
@RequestMapping("/order")
public class PurchaseOrderFactory {
	
	@Autowired
	private RabbitTemplate template;

	@Value("${rest.rabbitmq.exchange}") String exchange;
	@Value("${rest.rabbitmq.queue}") String queue;
	@Value("${rest.rabbitmq.routhingkey}") String routhingkey;
	
	@PostMapping("/{customerName}")
	public String bookOrder(@RequestBody PurchaseOrder order,@PathVariable String customerName) {
		order.setOrderId(UUID.randomUUID().toString());
		OrderStatus orderstatus = new OrderStatus(order,"ACCEPTED","order placed successfully by " + customerName);
		template.convertAndSend(routhingkey, orderstatus);
		return "SUCCESS";
		
	}
}
