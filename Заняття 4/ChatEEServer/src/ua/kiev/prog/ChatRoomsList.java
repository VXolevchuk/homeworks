package ua.kiev.prog;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class ChatRoomsList {
    private static final ChatRoomsList chrList = new ChatRoomsList();

    private final Gson gson;
    private final static  ArrayList<ChatRoom> list = new ArrayList<>();

    public static ChatRoomsList getInstance() {
        return chrList;
    }

    private ChatRoomsList() {
        gson = new GsonBuilder().create();
    }

    public synchronized void add(ChatRoom c) {
        list.add(c);
    }


    public String toJSON() {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(this);
    }

    public static ChatRoomsList fromJSON(String s) {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(s, ChatRoomsList.class);
    }

    public static ArrayList<String> getChatRooms(String part) {
        ArrayList<String> a = new ArrayList<>();
        for (ChatRoom c : list) {
            if(c.isParticipant(part)) {
                a.add(c.getName());
            }
        }
        return a;
    }

    public static ArrayList<String> getChatRooms() {
        ArrayList<String> a = new ArrayList<>();
        for (ChatRoom c : list) {
                a.add(c.getName());
        }
        return a;
    }

    public synchronized ChatRoom getRoom(String name) {
        ChatRoom cr = null;
        for (ChatRoom c : list) {
            if (c.getName().equals(name)) {
                cr = c;
            }
        }
        return cr;
    }





}
