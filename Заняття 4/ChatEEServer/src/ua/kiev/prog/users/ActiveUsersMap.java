package ua.kiev.prog.users;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;

public class ActiveUsersMap {
        private static final ActiveUsersMap aum = new ActiveUsersMap();

        private final Gson gson;

        private final HashMap<String, Boolean> map = new HashMap<>();

        public static ActiveUsersMap getInstance() {
            return aum;
        }

        private ActiveUsersMap() {
        gson = new GsonBuilder().create();
    }

        public synchronized void setStatus(String login, Boolean status) {
            map.put(login, status);
        }
        public synchronized Boolean get(String login){
            return map.get(login);
        }

        public boolean containsK(String login) { return map.containsKey(login);}

    }

