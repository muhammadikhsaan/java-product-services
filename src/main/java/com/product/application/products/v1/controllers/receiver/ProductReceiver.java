package com.product.application.products.v1.controllers.receiver;

import com.product.application.products.v1.models.broker.OrderQtyBroker;
import com.product.application.products.v1.services.interfaces.ProductServiceInterfaces;
import com.product.configuration.rabbitmq.RabbitMQConfiguration;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductReceiver {

    @Autowired
    private ProductServiceInterfaces productService;
    
    @RabbitListener(queues = RabbitMQConfiguration.SERVICE_ORDER_QUEUE)
    public void productOrder(OrderQtyBroker orderQtyBroker) {

    }

}
