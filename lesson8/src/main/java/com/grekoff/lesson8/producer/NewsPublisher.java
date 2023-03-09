package com.grekoff.lesson8.producer;

import com.rabbitmq.client.*;

public class NewsPublisher {
    private static final String EXCHANGE_NAME = "topic_exchange";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);

            String queueName = channel.queueDeclare().getQueue();
            String message;
            String keyNews;

            for (int i = 0; i < 20; i++) {
                if(i % 3 == 0) {
                    keyNews = "php";
                }else if(i % 2 == 0) {
                    keyNews = "c++";
                }else {
                    keyNews = "java";
                }
                message = keyNews + " news";
                channel.basicPublish(EXCHANGE_NAME, keyNews, null, message.getBytes("UTF-8"));
                System.out.println(" [x] Sent '" + message + "'");
            }
        }
    }
}
