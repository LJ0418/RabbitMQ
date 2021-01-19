package com.ifp.sendmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfig {

    @Bean(name = "testQueue")
    public Queue testQueue(){
        return new Queue("testQueue", true, false, false);
    }

    @Bean(name = "testExchange")
    public FanoutExchange testExchange(){
        return new FanoutExchange("testExchange");
    }

    @Bean
    public Binding exchangeAndQueue(){
        return BindingBuilder.bind(testQueue()).to(testExchange());
    }
}
