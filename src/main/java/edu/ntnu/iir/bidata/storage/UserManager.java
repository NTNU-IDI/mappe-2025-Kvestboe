package edu.ntnu.iir.bidata.storage;

import edu.ntnu.iir.bidata.model.User;
import java.util.HashMap;
import java.util.ArrayList;

public class UserManager {
    // load and save list of registered users
    // add new users
    // locate user's personal data folder

    HashMap<String, User> userMap =  new HashMap<>();

    public void saveUser(User user) {
        userMap.put(user.getName(), user);
    }

    public ArrayList<String> getUsers() {
        ArrayList<String> userNames = new ArrayList<>();
        for (User user : userMap.values()) {
            userNames.add(user.getName());
        }
        return userNames;
    }

    public User getUser(String name) {
        return userMap.get(name);
    }
}
