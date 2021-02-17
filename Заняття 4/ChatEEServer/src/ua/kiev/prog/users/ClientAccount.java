package ua.kiev.prog.users;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ClientAccount {
    private String login;
    private  String password;

    public ClientAccount(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String toJSON() {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(this);
    }

    public static ClientAccount fromJSON(String s) {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(s, ClientAccount.class);
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
