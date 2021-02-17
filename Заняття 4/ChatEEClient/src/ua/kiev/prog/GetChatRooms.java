package ua.kiev.prog;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class GetChatRooms {
    private final Gson gson;

    public GetChatRooms() {
        gson = new GsonBuilder().create();
    }

    public void getRooms () throws IOException {
        URL obj = new URL(Utils.getURL() + "/getrooms");
        HttpURLConnection conn = (HttpURLConnection) obj.openConnection();

        conn.setRequestMethod("GET");
        conn.setDoOutput(true);

        try (InputStream is = conn.getInputStream()) {
            byte[] buf = responseBodyToArray(is);
            String strBuf = new String(buf, StandardCharsets.UTF_8);
            ArrayList<String> list = gson.fromJson(strBuf, ArrayList.class);
            if(list != null) {
                System.out.println(list.toString());
            }
        }

    }

    private byte[] responseBodyToArray(InputStream is) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[10240];
        int r;

        do {
            r = is.read(buf);
            if (r > 0) bos.write(buf, 0, r);
        } while (r != -1);
        is.close();

        return bos.toByteArray();
    }
}
