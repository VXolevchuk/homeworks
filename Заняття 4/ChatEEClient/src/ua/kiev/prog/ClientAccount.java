package ua.kiev.prog;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ClientAccount {
    private String login;
    private String password;

    public ClientAccount() {

    }

    public ClientAccount(String log, String pass) {
        this.login = log;
        this.password = pass;

    }

    public String toJSON() {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(this);
    }

    public static ClientAccount fromJSON(String s) {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(s, ClientAccount.class);
    }

    public int createAccount() throws IOException {
        URL obj = new URL(Utils.getURL() + "/reg");
        HttpURLConnection conn = (HttpURLConnection) obj.openConnection();

        conn.setRequestMethod("POST");
        conn.setDoOutput(true);

        try (OutputStream os = conn.getOutputStream()) {
            String json = toJSON();
            os.write(json.getBytes(StandardCharsets.UTF_8));
            return conn.getResponseCode();
        }


    }

    public int login() throws IOException {
        URL obj = new URL(Utils.getURL() + "/login");
        HttpURLConnection conn = (HttpURLConnection) obj.openConnection();

        conn.setRequestMethod("POST");
        conn.setDoOutput(true);

        try (OutputStream os = conn.getOutputStream()) {
            String json = toJSON();
            os.write(json.getBytes(StandardCharsets.UTF_8));
            return conn.getResponseCode();
        }
    }


    public void startChat()  {
        try (Scanner scanner = new Scanner(System.in)) {
            Thread th = new Thread(new GetThread(this.login, 0));
            th.setDaemon(true);
            th.start();

            System.out.println("Enter your message: ");
            while (true) {
                String text = scanner.nextLine();
                if (text.isEmpty()) break;

                Message m = new Message(this.login, "all", text);
                int res = m.send(Utils.getURL() + "/add");

                if (res != 200) { // 200 OK
                    System.out.println("HTTP error occured: " + res);
                    return;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void startPrivateChat() {
        try (Scanner scanner = new Scanner(System.in)) {
            Thread th = new Thread(new GetThread(this.login, 1));
            th.setDaemon(true);
            th.start();

            System.out.println("Enter receiver:");
            String rec = scanner.nextLine();

            System.out.println("Enter your message: ");
            while (true) {
                String text = scanner.nextLine();
                if (text.isEmpty()) break;

                Message m = new Message(this.login, rec, text);
                int res = m.send(Utils.getURL() + "/add");

                if (res != 200) { // 200 OK
                    System.out.println("HTTP error occured: " + res);
                    return;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void getUsers() throws IOException {
        GetUsersList.showUsersList();
    }

    public void getStatus() throws IOException {
        GetUsersList.showUserStatus();

    }

    public void createChatRoom () {
        int resp = 0;
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter chat room name:");
            String name = scanner.next();
            ChatRoom chr = new ChatRoom(name, this.login);

            URL obj = new URL(Utils.getURL() + "/createroom");
            HttpURLConnection conn = (HttpURLConnection) obj.openConnection();

            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            try (OutputStream os = conn.getOutputStream()) {
                String json = chr.toJSON();
                os.write(json.getBytes(StandardCharsets.UTF_8));
                resp = conn.getResponseCode();
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        if (resp == 200) {
            System.out.println("Chat room created successfully!");
        } else {
            System.out.println("Something go wrong:(");
        }
    }

    public void enterChatRoom() throws IOException {
            int resp = 0;
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter chat room name");
            String name = sc.nextLine();

            URL obj = new URL(Utils.getURL() + "/enterroom?login=" + this.login + "&name=" + name);
            HttpURLConnection conn = (HttpURLConnection) obj.openConnection();

            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            resp = conn.getResponseCode();
        if (resp == 200) {
            System.out.println("You entered chat room created successfully!");
        } else {
            System.out.println("Something go wrong:(");
        }

    }

    public void getRoomsList() throws IOException {
        GetChatRooms gchr = new GetChatRooms();
         gchr.getRooms();
    }

    public void chatInChatRoom() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter wanted chat room:");
            String room = scanner.nextLine();

            Thread th = new Thread(new GetThread(this.login, room, 2));
            th.setDaemon(true);
            th.start();

            if (checkRoom(room)) {
                System.out.println("Enter your message: ");
                while (true) {
                    String text = scanner.nextLine();
                    if (text.isEmpty()) break;

                    Message m = new Message(this.login, room, text, 1);
                    int res = m.send(Utils.getURL() + "/add");

                    if (res != 200) { // 200 OK
                        System.out.println("HTTP error occured: " + res);
                        return;
                    }
                }
            } else {
                System.out.println("No such room");
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private boolean checkRoom(String name) throws IOException {
        boolean check;
        URL obj = new URL(Utils.getURL() + "/checkroom?login=" + this.login + "&name=" + name);
        HttpURLConnection conn = (HttpURLConnection) obj.openConnection();

        conn.setRequestMethod("GET");
        conn.setDoOutput(true);
        if (conn.getResponseCode() == 200) {
            check = true;
        } else {
            check = false;
        }
        return check;

    }

    public void exit() throws IOException{
        URL obj = new URL(Utils.getURL() + "/exit?login=" + this.login);
        HttpURLConnection conn = (HttpURLConnection) obj.openConnection();

        conn.setRequestMethod("GET");
        conn.setDoOutput(true);

         if (conn.getResponseCode() == 200) {
             System.out.println("Your exited successfully!");
         }
    }



}