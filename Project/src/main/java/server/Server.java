package server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Server {
    private List<String> messages;
    private Map<Integer, Float> addresses;

    public Server() {
        this.messages = new ArrayList<>();
        this.addresses = new HashMap<>();
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public Map<Integer, Float> getAddresses() {
        return addresses;
    }

    public void setAddresses(Map<Integer, Float> addresses) {
        this.addresses = addresses;
    }

}
