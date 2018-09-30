package protocolModel;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public interface Packet {

    default String toPacket() {
        Gson gson = new Gson();
        String json = gson.toJson(this);

        return json;
    }
}
