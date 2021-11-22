package com.mrtclk.listener;

import com.mrtclk.model.Notification;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @Author Murat Çelik on Kas, 2021
 */
@Service
public class NotificationListener {

    @RabbitListener(queues = "mrt.queue")
    public void handleMessage(Notification notification) {
        System.out.println("Message received..");
        System.out.println(notification.toString());
    }
}
