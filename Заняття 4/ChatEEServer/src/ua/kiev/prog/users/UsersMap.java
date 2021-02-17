package ua.kiev.prog.users;

import java.util.HashMap;
import java.util.Set;

public class UsersMap {
    private static final UsersMap um = new UsersMap();

    private final HashMap<String, String> map = new HashMap<>();

    public static UsersMap getInstance() {
        return um;
    }

    public synchronized void add(String login, String pass) {
        map.put(login, pass);
    }
    public synchronized String get(String login){
        return map.get(login);
    }

    public boolean containsK(String login) { return map.containsKey(login);}

    public synchronized Set<String> getLogins(){
        Set<String> keys = map.keySet();
        return keys;
    }




}
