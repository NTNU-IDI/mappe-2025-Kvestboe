package edu.ntnu.iir.bidata.ui;

import edu.ntnu.iir.bidata.model.Author;
import edu.ntnu.iir.bidata.storage.AuthorManager;
import java.util.ArrayList;

public class AuthorController {

  ConsoleView view;
  ConsoleInput input;

  public AuthorController(ConsoleView view,  ConsoleInput input) {
    this.view = view;
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
        view.printInvalidAction();
      }
    }

    return null;

  }

  private String authorMenu(AuthorManager authorManager, Author activeAuthor) {
    view.promptAuthorOptions(authorManager, activeAuthor);
    return input.read();

  }

  public Author addAuthor(AuthorManager authorManager) {
    view.printLine("Enter name: ");
    String name = input.read();
    authorManager.addAuthor(name);
    return authorManager.getAuthor(name);
  }
}
