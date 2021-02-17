package ua.kiev.prog;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.Set;

public class GetUsersList {

    private final Gson gson;

    public GetUsersList() {
        gson = new GsonBuilder().create();
    }

    public void showUsersList() throws IOException {
        URL obj = new URL(Utils.getURL() + "/users");
        HttpURLConnection conn = (HttpURLConnection) obj.openConnection();

        conn.setRequestMethod("GET");
        conn.setDoOutput(true);

        try (InputStream is = conn.getInputStream()) {
            byte[] buf = responseBodyToArray(is);
            String strBuf = new String(buf, StandardCharsets.UTF_8);

            Set<String> set = gson.fromJson(strBuf, Set.class);
            if (set != null) {
                System.out.println("Registrated users:" + set);
            }


        }



    }

    public void showUserStatus() throws IOException {
        Scanner sc = new Scanner(System.in);
        String login;
        System.out.println("Enter user nickname");
        login = sc.next();
        URL obj = new URL(Utils.getURL() + "/status?login=" + login);
        HttpURLConnection conn = (HttpURLConnection) obj.openConnection();

        conn.setRequestMethod("GET");
        conn.setDoOutput(true);

        try (InputStream is = conn.getInputStream()) {
            byte[] buf = responseBodyToArray(is);
            String strBuf = new String(buf, StandardCharsets.UTF_8);

            String status = gson.fromJson(strBuf, String.class);
            if (status != null) {
                System.out.println("User status is: " + status);
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
