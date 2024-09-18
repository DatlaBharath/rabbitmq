package com.rest.rabbit.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//
//=rest_exchange
//=rest_queue
//=rest_routingkey
@Configuration
public class MessageConfig {
	@Value("${rest.rabbitmq.exchange}") String exchange;
	@Value("${rest.rabbitmq.queue}") String queue;
	@Value("${rest.rabbitmq.routhingkey}") String routhingkey;
	
	@Bean
	public TopicExchange exchange(){
		return new TopicExchange(exchange);
	}
	
	@Bean 
	public Queue queue() {
		return new Queue(queue);
	}
	
	@Bean 
	public Binding binding(Queue queue,TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(routhingkey);
	}
	
	@Bean
	public MessageConverter converter() {
		return new Jackson2JsonMessageConverter();
	}
	
	@Bean
	public AmqpTemplate template(ConnectionFactory connectionFactory) {
		final RabbitTemplate template = new RabbitTemplate(connectionFactory);
		template.setMessageConverter(converter());
		return template;
	}
	
	
	
	
	
	
	
	
}
