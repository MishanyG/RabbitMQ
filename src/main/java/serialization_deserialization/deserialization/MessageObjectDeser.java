package serialization_deserialization.deserialization;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class MessageObjectDeser {
    public String product;
    public String category;
    public int price;

    public MessageObjectDeser() {
    }
}
