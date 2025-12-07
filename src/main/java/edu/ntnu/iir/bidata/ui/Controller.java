package edu.ntnu.iir.bidata.ui;

import edu.ntnu.iir.bidata.model.Author;
import edu.ntnu.iir.bidata.storage.AuthorManager;
import edu.ntnu.iir.bidata.storage.EntryManager;
import java.util.Scanner;

public class Controller {

  AuthorManager authorManager;
  EntryManager entryManager;

  ConsoleView view;
  ConsoleInput input;

  AuthorController authorController;
  EntryController entryController;
  SearchController searchController;



  Author author;

//  IoHandler ioHandler = null;

  /**
   * This method will initialize the diary, make objects of different classes.
   */
  public void initialize() {
    authorManager = new AuthorManager();
    entryManager = new EntryManager();

    view = new ConsoleView();
    input = new ConsoleInput(new Scanner(System.in), view);

    authorController = new AuthorController(view, input);
    entryController = new EntryController(authorController, view, input);
    searchController = new SearchController(authorController, input, view, entryController);

    author = authorController.addAuthor(authorManager);

  }

  /**
   * This method will start the diary program. Makes the flow of the program.
   */
  public void start() {

    if (authorManager != null && entryManager != null && view != null && input != null
        && authorController != null && entryController!= null && author != null) {
      boolean runProgram = true;

      while (runProgram) {
        assert entryManager != null;
        view.promptForMenuAction(entryManager);
        //String menuChoice = input.readLine(""); try this when finished
        String menuChoice = input.read();

        switch (menuChoice) {
          case "new" -> entryController.createEntry(entryManager, author, authorManager);

          case "prior" -> searchController.searchEntries(entryManager,authorManager);

          case "author" -> {
            Author newAuthor = authorController.authorOptions(authorManager, author);
            if (newAuthor != null) {
              author = newAuthor;
            }
          }

          case "stat" -> view.printStatistics(entryManager);

          case "exit" -> runProgram = false;

          default -> view.printInvalidAction();
        }
      }

    } else {
      initialize();
    }

  }


}
