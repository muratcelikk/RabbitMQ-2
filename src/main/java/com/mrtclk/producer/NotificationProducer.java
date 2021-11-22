package com.mrtclk.producer;

import com.mrtclk.model.Notification;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.UUID;

/**
 * @Author Murat Çelik on Kas, 2021
 */
@Service
public class NotificationProducer {

    @Value("${spring.rabbitmq.routingkey.name}")
    private String routingKey;

    @Value("${spring.rabbitmq.exchange.name}")
    String exchange;

    @PostConstruct
    public void init() {
        Notification notification = new Notification();
        notification.setNotificationId(UUID.randomUUID().toString());
        notification.setCreatedAt(new Date());
        notification.setMessage("WELCOME TO RABBİTMQ");
        notification.setSeen(Boolean.FALSE);

        try {
            Thread th = new Thread();
            th.start();
            sendToQueue(notification);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendToQueue(Notification notification) throws InterruptedException {
        System.out.println(("Notification sent ID: " + notification.getNotificationId()));
        rabbitTemplate.convertAndSend(exchange, routingKey, notification); //convertAndSend (String Routing, Object object)
        Thread.sleep(7000);
    }
}
