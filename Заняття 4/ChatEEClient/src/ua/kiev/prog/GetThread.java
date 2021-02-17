package ua.kiev.prog;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class GetThread implements Runnable {
    private final Gson gson;
    private int n;
    private String hearer;
    private int k;
    private String chatRoom;

    public GetThread() {
        gson = new GsonBuilder().create();
    }

    public GetThread(String login, int k) {
        gson = new GsonBuilder().create();
        this.hearer = login;
        this.k = k;
    }

    public GetThread(String login, String chatRoom, int k) {
        gson = new GsonBuilder().create();
        this.hearer = login;
        this.chatRoom = chatRoom;
        this.k = k;
    }

    @Override
    public void run() {
        try {
            while ( ! Thread.interrupted()) {
                URL url = new URL(Utils.getURL() + "/get?from=" + n + "&asks=" + hearer);
                HttpURLConnection http = (HttpURLConnection) url.openConnection();

                try (InputStream is = http.getInputStream()) {
                    byte[] buf = responseBodyToArray(is);
                    String strBuf = new String(buf, StandardCharsets.UTF_8);

                    JsonMessages list = gson.fromJson(strBuf, JsonMessages.class);
                    if (list != null) {
                        for (Message m : list.getList()) {
                            if (hearer.equals(m.getFrom())) System.out.println(m.toString());
                            if (k == 0) { if (m.getTo().equals("all")  && !m.getFrom().equals(hearer)) { System.out.println(m.toString()); } }
                            if (k == 1) { if (m.getTo().equals(hearer)) { System.out.println(m.toString()); } }
                            if (k == 2) { if (m.getChatRoom().equals(chatRoom)) { System.out.println(m.toString()); } }
                            n++;
                        }
                    }
                }

                Thread.sleep(500);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
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
