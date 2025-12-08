package edu.ntnu.iir.bidata.ui;

import static edu.ntnu.iir.bidata.ui.ConsoleView.*;

import edu.ntnu.iir.bidata.model.Author;
import edu.ntnu.iir.bidata.storage.AuthorManager;

/**
  * Responsible for the flow of the author options in the diary
 */
public class AuthorController {

  /**
   * Responsible for the input needed for author.
   */
  ConsoleInput input;

  /**
   * Contructor for author controller.
   *
   * @param input input object from ConsoleInput
   */
  public AuthorController(ConsoleInput input) {
    this.input = input;
  }

  /**
   * Responsible for handling author options.
   *
   * @param authorManager authorManager represents the register of authors
   * @param author author is the current active author
   * @return the new author that is made or picked
   */
  public Author authorOptions(AuthorManager authorManager, Author author) {
    boolean running = true;

    while (running) {
      String choice = authorMenu(authorManager, author);
      if (authorManager.getAuthors().contains(choice)) {
        return authorManager.getAuthor(choice);
      } else if (choice.equals("new")) {
        return addAuthor(authorManager);
      } else if (choice.equals("none")) {
        running = false;
      } else {
        ConsoleView.printInvalidAction();
      }
    }

    return null;

  }

  /**
   * This method will add an author.
   *
   * @param authorManager authorManager represents the register of authors
   * @return a new author the user just made
   */
  public Author addAuthor(AuthorManager authorManager) {
    printLine("Enter name: ");
    String name = input.read();
    authorManager.addAuthor(name);
    return authorManager.getAuthor(name);
  }

  /**
   * This method will make ConsoleView print out list of authors.
   *
   * @param authorManager authorManager represents the register of authors
   * @param activeAuthor activeAuthor is the current active author
   * @return returns the pick of the user
   */
  private String authorMenu(AuthorManager authorManager, Author activeAuthor) {
    promptAuthorOptions(authorManager, activeAuthor);
    return input.read();

  }

  /**
   * This method will make user pick already existing author.
   *
   * @param authorManager authorManager represents the register of authors
   * @return the author that the user picks
   */
  public Author pickAuthor(AuthorManager authorManager) {
    while (true) {
      promptAuthorPicker(authorManager);
      String choice = input.read();
      if (authorManager.getAuthors().contains(choice)) {
        return authorManager.getAuthor(choice);
      } else if (choice.equals("none")) {
        return null;
      } else {
        printInvalidAction();
      }
    }
  }

}
