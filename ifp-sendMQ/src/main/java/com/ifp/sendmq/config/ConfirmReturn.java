package com.ifp.sendmq.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 消息发送成功的回调
 * 需要开启
 * # 开启发送确认
 * publisher-confirms: true
 * @author hw
 * @date 2019-07-22 15:44
 **/
@Slf4j
@Component
public class ConfirmReturn implements RabbitTemplate.ConfirmCallback {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void initRabbitTemplate(){
        rabbitTemplate.setConfirmCallback(this);
    }

    /**
     * 发布者确认的回调。
     *
     * @param correlationData 回调的相关数据。
     * @param ack             ack为真，nack为假
     * @param cause           一个可选的原因，用于nack，如果可用，否则为空。
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack) {
            log.info("消息成功发送到exchange:" + correlationData.getId());
        } else {
            log.error("消息发送exchange失败:" + cause);
        }
    }
}
