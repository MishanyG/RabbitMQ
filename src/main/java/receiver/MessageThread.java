package receiver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import serialization_deserialization.deserialization.MessageObjectDeser;

import java.io.StringReader;

public class MessageThread extends Thread {
    private Channel channel;
    private String message;
    private long tag;

    public MessageThread(Channel channel, String message, long tag) {
        this.channel = channel;
        this.message = message;
        this.tag = tag;
    }

    @Override
    public void run() {
        try {
            StringReader reader = new StringReader(message);
            ObjectMapper mapper = new ObjectMapper();
            MessageObjectDeser messageObjectDeser = mapper.readValue(reader, MessageObjectDeser.class);
            System.out.println("Message received " + messageObjectDeser.category + ":" + messageObjectDeser.product + ":" + messageObjectDeser.price);
            sleep(3000);
            channel.basicAck(tag, false);
            System.out.println("Message deleted " + messageObjectDeser.category + ":" + messageObjectDeser.product + ":" + messageObjectDeser.price);
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
        }
    }
}
