package edu.ntnu.iir.bidata.ui;

import static edu.ntnu.iir.bidata.ui.ConsoleView.*;

import edu.ntnu.iir.bidata.model.Author;
import edu.ntnu.iir.bidata.model.Statistic;
import edu.ntnu.iir.bidata.storage.AuthorManager;
import edu.ntnu.iir.bidata.storage.EntryManager;
import java.util.Scanner;

public class Controller {
  Statistic statistic;

  AuthorManager authorManager;
  EntryManager entryManager;

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
   * This method will start the diary program. Makes the flow of the program.
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
