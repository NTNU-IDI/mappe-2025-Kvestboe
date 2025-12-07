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
        System.out.println("Invalid choice...");
      }
    }

    return null;

  }

  private String authorMenu(AuthorManager authorManager, Author activeAuthor) {
    view.promptAuthorOptions(authorManager, activeAuthor);


  }

  public Author addAuthor(AuthorManager authorManager) {
    System.out.print("What is your name: ");
    String name = input.nextLine();
    authorManager.addAuthor(name);
    return authorManager.getAuthor(name);
  }
}
