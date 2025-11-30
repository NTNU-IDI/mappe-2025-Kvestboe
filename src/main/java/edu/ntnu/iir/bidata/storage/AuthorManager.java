package edu.ntnu.iir.bidata.storage;

import edu.ntnu.iir.bidata.model.Author;
import java.util.HashMap;
import java.util.ArrayList;

public class AuthorManager {
    // load and save list of registered users
    // add new users
    // locate user's personal data folder

    // create list for users to be saved on
    private final HashMap<String, Author> authorMap =  new HashMap<>();

    // method for getting a list of the usernames
    public ArrayList<String> getUsers() {
        ArrayList<String> userNames = new ArrayList<>();
        for (Author author : authorMap.values()) {
            userNames.add(author.getName());
        }
        return userNames;
    }

    // method for getting specific user
    public Author getUser(String name) {
        return authorMap.get(name);
    }

    // method for creating a new user, which also saves it
    public void addUser(String name) {
        Author author = new Author(name);
        authorMap.put(name, author);

    }
}
