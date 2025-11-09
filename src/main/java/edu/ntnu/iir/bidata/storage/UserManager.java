package edu.ntnu.iir.bidata.storage;

import edu.ntnu.iir.bidata.model.User;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Scanner;

public class UserManager {
    // load and save list of registered users
    // add new users
    // locate user's personal data folder

    // create list for users to be saved on
    private HashMap<String, User> userMap =  new HashMap<>();

    // method for getting a list of the usernames
    public ArrayList<String> getUsers() {
        ArrayList<String> userNames = new ArrayList<>();
        for (User user : userMap.values()) {
            userNames.add(user.getName());
        }
        return userNames;
    }

    // method for getting specific user
    public User getUser(String name) {
        return userMap.get(name);
    }

    // method for creating a new user, which also saves it
    public void addUser(String name) {
        User user = new User(name);
        userMap.put(name, user);

    }
}
