package com.agb.factorycontrol.communications;

import com.agb.factorycontrol.communications.ReceiveMessageHandler;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * See example at: https://www.rabbitmq.com/tutorials/tutorial-one-java.html
 */
public class Communications {
    private final static String   SEND_MESSAGE_QUEUE_NAME = "control";
    private final static String   RECEIVE_MESSAGE_QUEUE_NAME = "sensors";
    private final static String   IP_ADDRESS = "10.0.0.104";
    private ReceiveMessageHandler mReceiveMessageHandler;
    private Channel               mSendChannel;
    private Channel               mReceiveChannel;

    public Communications(ReceiveMessageHandler receiveMessageHandler) {
        try {
            mReceiveMessageHandler = receiveMessageHandler;
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost(IP_ADDRESS);
            Connection connection = factory.newConnection();
            mSendChannel = connection.createChannel();
            mSendChannel.queueDeclare(SEND_MESSAGE_QUEUE_NAME, false, false, false, null);            

            mReceiveChannel = connection.createChannel();
            mReceiveChannel.queueDeclare(RECEIVE_MESSAGE_QUEUE_NAME, false, false, false, null);
            Consumer messageConsumer = new DefaultConsumer(mReceiveChannel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                         AMQP.BasicProperties properties, byte[] body)
                throws IOException {
                    String message = new String(body, "UTF-8");
                    System.out.println("Received '" + message + "'");
                    mReceiveMessageHandler.handleMessage(message);
                    System.out.println("handler complete");
              }
            };
            mReceiveChannel.basicConsume(RECEIVE_MESSAGE_QUEUE_NAME, true, messageConsumer);            
            
        } catch (IOException | TimeoutException ex) {
            Logger.getLogger(Communications.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sendMessage(String message) {
        try {
            mSendChannel.basicPublish("", SEND_MESSAGE_QUEUE_NAME, null, message.getBytes("UTF-8"));
        } catch (IOException ex) {
            Logger.getLogger(Communications.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Sent '" + message + "'");
    }
}