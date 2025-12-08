package edu.ntnu.iir.bidata.ui;

import static edu.ntnu.iir.bidata.ui.ConsoleView.*;

import edu.ntnu.iir.bidata.model.Author;
import edu.ntnu.iir.bidata.model.Statistic;
import edu.ntnu.iir.bidata.storage.AuthorManager;
import edu.ntnu.iir.bidata.storage.EntryManager;
import java.util.Scanner;

/**
 * Controller for the diary application.
 * This represents the flow of the main menu for the diary.
 */
public class Controller {
  Statistic statistic;

  AuthorManager authorManager;
  EntryManager entryManager;

  ConsoleInput input;

  AuthorController authorController;
  EntryController entryController;
  SearchController searchController;

  Author author;

  /**
   * Initialize the diary application.
   * Creates the Statistic, AuthorManager, EntryManager, ConsoleInput and
   * controller instances, then prompts the user to add the initial author.
   * This method must be called before start() when components are not yet set up.
   */
  public void initialize() {
    statistic  = new Statistic();

    authorManager = new AuthorManager();
    entryManager = new EntryManager(statistic);

    input = new ConsoleInput(new Scanner(System.in));

    authorController = new AuthorController(input);
    entryController = new EntryController(authorController,  input);
    searchController = new SearchController(authorController, input,  entryController);

    author = authorController.addAuthor(authorManager);

  }

  /**
   * Start the diary program and run the main loop.
   * This method repeatedly show the main menu,
   * reads the user's choice and directs the flow.
   */
  public void start() {

    if (authorManager != null && entryManager != null && input != null
        && authorController != null && entryController!= null && author != null) {
      boolean runProgram = true;

      while (runProgram) {
        assert entryManager != null;
        promptForMenuAction(entryManager);
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

          case "stat" -> printStatistics(entryManager);

          case "exit" -> runProgram = false;

          default -> printInvalidAction();
        }
      }

    } else {
      initialize();
    }

  }

}
