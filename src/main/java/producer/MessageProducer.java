package producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import serialization_deserialization.serialization.MessageObjectSer;

import java.io.StringWriter;

public class MessageProducer {
    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setVirtualHost("/");
        factory.setHost("127.0.0.1");
        factory.setPort(5672);
        Connection conn = factory.newConnection();
        Channel channel = conn.createChannel();
        String exchangeName = "myExchange";
        String routingKey = "testRoute";

        MessageObjectSer messageObjectSer = new MessageObjectSer();
        messageObjectSer.category = "mobile";
        messageObjectSer.product = "LG";
        messageObjectSer.price = 1200;

        StringWriter writer = new StringWriter();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(writer, messageObjectSer);
        String result = writer.toString();

        byte[] messageBodyBytes = result.getBytes();
        channel.basicPublish(exchangeName, routingKey, MessageProperties.PERSISTENT_TEXT_PLAIN, messageBodyBytes);
        channel.close();
        conn.close();
    }
}
