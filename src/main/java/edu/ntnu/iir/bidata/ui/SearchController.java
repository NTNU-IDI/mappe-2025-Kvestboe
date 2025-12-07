package edu.ntnu.iir.bidata.ui;

import edu.ntnu.iir.bidata.model.Author;
import edu.ntnu.iir.bidata.model.Entry;
import edu.ntnu.iir.bidata.storage.AuthorManager;
import edu.ntnu.iir.bidata.storage.EntryManager;
import edu.ntnu.iir.bidata.utils.EntrySort;
import java.time.LocalDate;
import java.util.HashMap;

public class SearchController {

  EntryManager entryManager;
  AuthorController authorController;
  ConsoleInput input;
  ConsoleView view;
  EntrySort sort;
  EntryController entryController;

  public SearchController(ConsoleInput input,  ConsoleView view, EntryController entryController) {
    this.input = input;
    this.view = view;
    this.entryController = entryController;
    this.sort = new EntrySort();
  }

  public void searchEntries(EntryManager entryManager, AuthorManager authorManager, Author author) {
    boolean running = true;
    boolean valid = true;
    while(running) {
      view.promptForSearchAction();
      String choice = input.read();
      int key = -1;
      switch (choice) {
        case "all" -> key = getAllEntries(entryManager);
        case "title" -> key = getEntriesTitle(entryManager);
        case "tag" -> key = getEntriesTag(entryManager);
        case "author" -> key = getEntriesAuthor(entryManager, authorManager, author);
        case "date" -> key = getEntriesDate(entryManager);
        case "period" -> key = getEntriesPeriod(entryManager);
        case "none" -> running = false;
        default -> valid = false;
      }
      if (running && valid) {
        if (key != -1) {
          System.out.println("funker");
          Entry entry = entryManager.getEntry(key);
          entryController.editDiary(entry, authorManager, entryManager);
          entryManager.updateEntry(key, entry);
        }
      }
    }
  }


  private int getAllEntries(EntryManager entryManager) {
    HashMap<Integer, Entry> entries = entryManager.getEntries();
    view.printEntries(entries);
    return input.readInt("Write the number of the diary you want to pick (-1 for none): ");
  }

  private int getEntriesTitle(EntryManager entryManager) {
    HashMap<Integer, Entry> diary = entryManager.getEntries();
    String title = input.readLine("Write in the title you want to search by: ");
    HashMap<Integer, Entry> entries = sort.searchTitle(diary, title);
    view.printEntries(entries);
    return input.readInt("Write the number of the diary you want to pick (-1 for none): ");

  }

  private int getEntriesTag(EntryManager entryManager) {
    HashMap<Integer, Entry> diary = entryManager.getEntries();
    String tag = input.readLine("Write in the tag you want to search by: ");
    HashMap<Integer, Entry> entries = sort.searchTag(diary, tag);
    view.printEntries(entries);
    return input.readInt("Write the number of the diary you want to pick (-1 for none): ");
  }

  private int getEntriesAuthor(EntryManager entryManager, AuthorManager authorManager, Author acitveAuthor) {
    HashMap<Integer, Entry> diary = entryManager.getEntries();
    Author author = authorController.authorOptions(authorManager, acitveAuthor);
    HashMap<Integer, Entry> entries = sort.searchAuthor(diary, author);
    view.printEntries(entries);
    return input.readInt("Write the number of the diary you want to pick (-1 for none): ");

  }

  private int getEntriesDate(EntryManager entryManager) {
    HashMap<Integer, Entry> diary = entryManager.getEntries();
    LocalDate localDate = input.readDate();
    HashMap<Integer, Entry> entries = sort.searchDate(diary, localDate);
    view.printEntries(entries);
    return input.readInt("Write the number of the diary you want to pick (-1 for none): ");
  }

  private int getEntriesPeriod(EntryManager entryManager) {
    HashMap<Integer, Entry> diary = entryManager.getEntries();
    LocalDate date1 = input.readDate();
    LocalDate date2 = input.readDate();
    HashMap<Integer, Entry> entries = sort.searchPeriod(diary, date1, date2);
    view.printEntries(entries);
    return input.readInt("Write the number of the diary you want to pick (-1 for none): ");
  }


}
