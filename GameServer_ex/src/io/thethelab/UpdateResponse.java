package io.thethelab;

import model.Packet;

public class UpdateResponse implements Packet {
    private final String type;
    private final User[] users;

    public UpdateResponse(User[] users) {
        this.type = "Update";
        this.users = users;
    }
}
