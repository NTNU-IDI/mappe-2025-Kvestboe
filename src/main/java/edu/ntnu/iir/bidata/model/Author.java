package edu.ntnu.iir.bidata.model;

/**
 * Represents the user object
 */

public class Author {
    // binds together user's diary entries, settings and storage files
    // user information like name, and potentially password

    private final String name;

    /**
     * @param name sets name of user
     */
    public Author(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
