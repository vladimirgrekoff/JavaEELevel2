package com.grekoff.lesson8.consumer;

import com.rabbitmq.client.*;

import java.util.Objects;
import java.util.Scanner;

public class ExchangeReceiverApp {
    private static final String EXCHANGE_NAME = "topic_exchange";


    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);

        String queueName = channel.queueDeclare().getQueue();
        System.out.println("Имя очереди: " + queueName);

        Scanner sc = new Scanner(System.in);
        String keyNews = selectNews(sc);

        channel.queueBind(queueName, EXCHANGE_NAME, keyNews);

        System.out.println(" [*] Ожидание сообщений");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Получены '" + message + "'");
        };
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {
        });

    }

    private static String selectNews(Scanner sc) {
        System.out.println("Введите один из каналов новостей: php, c++ или java");
        String keyNews;
        keyNews = sc.next();
        return keyNews;
    }
}
