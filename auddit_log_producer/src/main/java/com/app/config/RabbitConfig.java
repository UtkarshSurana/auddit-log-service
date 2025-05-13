package com.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;

@Configuration
public class RabbitConfig {

	@PostConstruct
	public void init() {
		System.out.println("RabbitConfig loaded");
	}

	@Bean
	public TopicExchange exchange() {
		return new TopicExchange("audit-exchange");
	}

	@Bean
	public Queue auditQueue() {
		return new Queue("audit-queue", false);
	}

	@Bean
	public Binding binding() {
		return BindingBuilder.bind(auditQueue()).to(exchange()).with("audit-routing-key");
	}
	
	@Bean
	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
	    RabbitTemplate template = new RabbitTemplate(connectionFactory);
	    template.setMessageConverter(new Jackson2JsonMessageConverter());
	    return template;
	}
}
