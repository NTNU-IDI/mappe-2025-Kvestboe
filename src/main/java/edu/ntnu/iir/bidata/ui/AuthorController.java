package edu.ntnu.iir.bidata.ui;

import static edu.ntnu.iir.bidata.ui.ConsoleView.*;

import edu.ntnu.iir.bidata.model.Author;
import edu.ntnu.iir.bidata.storage.AuthorManager;


public class AuthorController {

  ConsoleInput input;

  public AuthorController(ConsoleInput input) {
    this.input = input;
  }

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

  public Author addAuthor(AuthorManager authorManager) {
    printLine("Enter name: ");
    String name = input.read();
    authorManager.addAuthor(name);
    return authorManager.getAuthor(name);
  }

  private String authorMenu(AuthorManager authorManager, Author activeAuthor) {
    promptAuthorOptions(authorManager, activeAuthor);
    return input.read();

  }

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
