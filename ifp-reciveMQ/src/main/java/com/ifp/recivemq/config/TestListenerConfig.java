package com.ifp.recivemq.config;

import com.alibaba.fastjson.JSON;
import com.ifp.common.Model.BaseMessage;
import com.ifp.common.Model.MQName;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class TestListenerConfig {

    @RabbitListener(bindings = {
        @QueueBinding(value = @Queue(value = MQName.test.QUEUE),
            exchange = @Exchange(value = MQName.test.EXCHANGE),
            key = {MQName.test.ROUTING_key})
    })
    @RabbitHandler
    public void execute(@Payload Message message, Channel channel, BaseMessage msg) throws IOException {
        try {
            if (msg == null) System.out.println("传递参数为空");
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            System.out.println(JSON.toJSON(msg));
            log.info("成功接收到消息：" + JSON.toJSONString(msg));
        } catch (Exception e){
            if (message.getMessageProperties().getRedelivered()) {
                log.error("消息已重复处理失败,拒绝再次接收...");
                //b——multiple：true批处理参数，
                channel.basicReject(message.getMessageProperties().getDeliveryTag(), false); // 拒绝消息
            } else {
                log.error("消息即将再次返回队列处理...");
                // b1——queue:true返回队列，false不返回队列；
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            }
        }

    };
}
