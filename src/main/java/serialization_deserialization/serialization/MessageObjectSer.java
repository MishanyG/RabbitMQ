package serialization_deserialization.serialization;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class MessageObjectSer {
    public String product;
    public String category;
    public int price;

    public MessageObjectSer() {
    }
}
