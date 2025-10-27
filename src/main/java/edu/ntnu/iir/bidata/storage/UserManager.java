package edu.ntnu.iir.bidata.storage;

import edu.ntnu.iir.bidata.model.User;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Scanner;

public class UserManager {
    // load and save list of registered users
    // add new users
    // locate user's personal data folder

    private HashMap<String, User> userMap =  new HashMap<>();

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

    public User createUser(Scanner input) {
        System.out.print("What is your name: ");
        User author = new User(input.nextLine());
        System.out.println("Hello, " +  author.getName() + "!");
        userMap.put(author.getName(), author);
        return author;
    }
}
