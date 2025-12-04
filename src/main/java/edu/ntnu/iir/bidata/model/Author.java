package edu.ntnu.iir.bidata.model;

/**
 * The author class represents a user of the diary application.
 */
public class Author {
  // binds together user's diary entries, settings and storage files
  // user information like name, and potentially password

  /**
   * The name of the author.
   */
  private final String name;

  /**
   * Constructor for author class.
   *
   * @param name the name of the author
   */
  public Author(String name) {
    this.name = name;
  }

  /**
   * Getter for author name.
   *
   * @return name of author
   */
  public String getName() {
    return name;
  }

}
