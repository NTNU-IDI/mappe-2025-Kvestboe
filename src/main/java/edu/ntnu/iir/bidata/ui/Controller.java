package edu.ntnu.iir.bidata.ui;

import edu.ntnu.iir.bidata.model.Author;
import edu.ntnu.iir.bidata.model.Entry;
import edu.ntnu.iir.bidata.storage.AuthorManager;
import edu.ntnu.iir.bidata.storage.EntryManager;
import java.util.ArrayList;
import java.util.Scanner;

public class Controller {

  AuthorManager authorManager;
  EntryManager entryManager;

  ConsoleView view;
  ConsoleInput input;

  AuthorController authorController;
  EntryController entryController;



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

    author = authorController.addAuthor(authorManager);

  }

  /**
   * This method will start the diary program. Makes the flow of the program.
   */
  public void start() {

    if (authorManager != null || entryManager != null || view != null || input != null
        || authorController != null || entryController!= null || author != null) {
      boolean runProgram = true;

      while (runProgram) {
        assert entryManager != null;
        view.promptForMenuAction(entryManager);
        //String menuChoice = input.readLine(""); try this when finished
        String menuChoice = input.read();

        switch (menuChoice) {
          case "new" -> entryController.createEntry(entryManager, author, authorManager);

          // case "prior" -> searchEntries(entryManager, author, authorManager); no function to search yet

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

//  public void newDiary(EntryManager entryManager, Author author, AuthorManager authorManager) {
//    String title = input.readLine("What is the title of the entry you want to create?");
//
//    ArrayList<String> tags = input.readTags("Write in tags with a space between:");
//
//    String content = input.readMultiline();
//
//    Entry diary= new Entry(author, title, tags, content);
//    int entry = entryManager.addEntry(diary);
//
//    editDiary(entryManager.getEntry(entry), authorManager, entryManager);
//
//  }

  //this method should belong to another class
//  /**
//   * This method will check that the user is valid.
//   */
//
//
//  private void editContent(Entry entry) {
//    boolean running = true;
//    while (running) {
//      view.promptForContentAction();
//      String choice = input.read();
//      switch (choice) {
//        case "read" -> view.printLine(entry.getContent());
//        case "write" -> writeContent(entry);
//        case "add" -> addContent(entry);
//        case "none" -> running = false;
//        default  -> view.printInvalidAction();
//
//      }
//    }
//
//  }

//  public void searchEntries(EntryManager entryManager, Author author, AuthorManager authorManager) {
//
//    boolean running = true;
//    boolean valid = true;
//    while (running) {
//      String choice = priorEntriesMenu();
//      Entry entry = null;
//      switch (choice) {
//        case "all" -> entry = getAllEntries(entryManager);
//        case "title" -> entry = getEntriesTitle(entryManager);
//        case "tag" -> entry = getEntriesTag(entryManager);
//        case "author" -> entry = getEntriesAuthor(entryManager, author, authorManager);
//        case "date" -> entry = getEntriesDate(entryManager);
//        case "period" -> entry = getEntriesPeriod(entryManager);
//        case "none" -> running = false;
//        default -> valid = false;
//      }
//      if (running && valid) {
//        if (entry != null) {
//          editDiary(entry, authorManager, entryManager);
//        }
//      }
//    }
//
//  }

}
