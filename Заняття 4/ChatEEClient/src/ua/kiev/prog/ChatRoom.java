package ua.kiev.prog;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class ChatRoom {
    private String name;
    private String owner;
    private final ArrayList<String> participants = new ArrayList<>();

    public ChatRoom(String name, String owner) {
        this.name = name;
        this.owner = owner;
    }

    public String toJSON() {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(this);
    }

    public static Message fromJSON(String s) {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(s, Message.class);
    }

    @Override
    public String toString() {
        return new StringBuilder().append("[").
                append( "Name: ").append(name).append(", Owner: ").append(owner)
                .append("] ").append(participants)
                .toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public ArrayList<String> getParticipants() {
        return participants;
    }




}
