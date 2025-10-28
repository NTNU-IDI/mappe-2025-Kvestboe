package edu.ntnu.iir.bidata.model;

public class User {
    // binds together user's diary entries, settings and storage files
    // user information like name, and potentially password

    String name;

    public User(String inputName) {
        name = inputName;
    }

    public String getName() {
        return name;
    }


}
