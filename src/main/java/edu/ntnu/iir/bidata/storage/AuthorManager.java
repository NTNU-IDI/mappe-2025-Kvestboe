package edu.ntnu.iir.bidata.storage;

import edu.ntnu.iir.bidata.model.Author;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Represents a register of authors in the diary application.
 */
public class AuthorManager {

  /**
   * The authors of the diary.
   */
  private final HashMap<String, Author> authorMap = new HashMap<>();

  /**
   * Getter for the authors names in the diary.
   *
   * @return a list of author names
   */
  public ArrayList<String> getAuthors() {
    ArrayList<String> userNames = new ArrayList<>();
    for (Author author : authorMap.values()) {
      userNames.add(author.getName());
    }
    return userNames;
  }

  /**
   * Getter for a single author in the diary, based on their name.
   *
   * @param name name of the author
   * @return an Author object
   */
  public Author getAuthor(String name) {
    return authorMap.get(name);
  }

  /**
   * Method for adding a new author in the diary.
   *
   * @param name name of the new author
   */
  public void addAuthor(String name) {
    Author author = new Author(name);
    authorMap.put(name, author);
  }
}
