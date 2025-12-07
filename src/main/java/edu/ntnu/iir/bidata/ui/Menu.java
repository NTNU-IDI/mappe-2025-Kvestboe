package edu.ntnu.iir.bidata.ui;

import edu.ntnu.iir.bidata.model.Author;
import edu.ntnu.iir.bidata.storage.AuthorManager;
import edu.ntnu.iir.bidata.storage.EntryManager;
import java.util.Scanner;

/**
 * This class represents the different functions of the diary.
 */
public class Menu {

  /**
   * This method will print the main menu.
   */
  public void showMenu() {
    if (entryManager.getEntries().isEmpty()) {
      System.out.println("Select your option:");
      System.out.println("new: new entry.");
      System.out.println("author: change author.");
      System.out.println("exit: exit.");
      System.out.print("> ");

    } else {
      System.out.println("Select your option:");
      System.out.println("new: new entry.");
      System.out.println("prior: view prior entries.");
      System.out.println("author: change author.");
      System.out.println("stat: show the statistics of the diary.");
      System.out.println("exit: exit.");
      System.out.print("> ");

    }
  }

  // this is part of the new code
  Scanner input = null;

  AuthorManager authorManager = null;
  EntryManager entryManager = null;
  Author author = null;

  IoHandler ioHandler = null;

  /**
   * This method will initialize the diary, make objects of different classes.
   */
  public void initialize() {
    input = new Scanner(System.in);
    authorManager = new AuthorManager();
    entryManager = new EntryManager();

    ioHandler = new IoHandler();
    author = ioHandler.addAuthor(authorManager);

  }

  /**
   * This method will start the diary program. Makes the flow of the program.
   */
  public void start() {

    if (input != null || authorManager != null || entryManager != null || ioHandler != null
        || author != null) {
      boolean runProgram = true;

      while (runProgram) {
        showMenu();
        String menuChoice = input.nextLine();

        switch (menuChoice) {
          case "new" -> ioHandler.newDiary(entryManager, author, authorManager);

          case "prior" -> ioHandler.priorEntries(entryManager, author, authorManager);

          case "author" -> checkUser();

          case "stat" -> ioHandler.statistics(entryManager);

          case "exit" -> runProgram = false;

          default -> System.out.println("Invalid choice");
        }
      }

    } else {
      initialize();
    }

  }

  /**
   * This method will check that the user is valid.
   */
  private void checkUser() {
    Author newAuthor = ioHandler.authorSetting(authorManager, author);
    if (newAuthor != null) {
      author = newAuthor;
    }
  }
}
