package edu.ntnu.iir.bidata.ui;

import edu.ntnu.iir.bidata.model.Author;
import edu.ntnu.iir.bidata.model.Entry;
import edu.ntnu.iir.bidata.storage.AuthorManager;
import edu.ntnu.iir.bidata.storage.EntryManager;
import edu.ntnu.iir.bidata.utils.EntrySort;
import java.time.LocalDate;
import java.util.HashMap;

public class SearchController {

  AuthorController authorController;
  ConsoleInput input;
  ConsoleView view;
  EntrySort sort;
  EntryController entryController;

  public SearchController(AuthorController authorController,ConsoleInput input,  ConsoleView view, EntryController entryController) {
    this.authorController = authorController;
    this.input = input;
    this.view = view;
    this.entryController = entryController;
    this.sort = new EntrySort();
  }

  public void searchEntries(EntryManager entryManager, AuthorManager authorManager) {
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
        case "author" -> key = getEntriesAuthor(entryManager, authorManager);
        case "date" -> key = getEntriesDate(entryManager);
        case "period" -> key = getEntriesPeriod(entryManager);
        case "none" -> running = false;
        default -> valid = false;
      }
      if (running && valid) {
        if (key != -1) {
          entryController.editDiary(key, authorManager, entryManager);
        }
      }
    }
  }


  private int getAllEntries(EntryManager entryManager) {
    HashMap<Integer, Entry> entries = entryManager.getDiary();
    view.printEntries(entries);
    return input.readInt("Write the number of the diary you want to pick (-1 for none): ");
  }

  private int getEntriesTitle(EntryManager entryManager) {
    HashMap<Integer, Entry> diary = entryManager.getDiary();
    String title = input.readLine("Write in the title you want to search by: ");
    HashMap<Integer, Entry> entries = sort.searchTitle(diary, title);
    if (!entries.isEmpty()) {
      view.printEntries(entries);
      return input.readInt("Write the number of the diary you want to pick (-1 for none): ");
    } else return -1;

  }

  private int getEntriesTag(EntryManager entryManager) {
    HashMap<Integer, Entry> diary = entryManager.getDiary();
    String tag = input.readLine("Write in the tag you want to search by: ");
    HashMap<Integer, Entry> entries = sort.searchTag(diary, tag);

    if (view.printEntries(entries))
      return input.readInt("Write the number of the diary you want to pick (-1 for none): ");
    else return -1;
  }

  private int getEntriesAuthor(EntryManager entryManager, AuthorManager authorManager) {
    HashMap<Integer, Entry> diary = entryManager.getDiary();
    Author author = authorController.pickAuthor(authorManager);
    HashMap<Integer, Entry> entries = sort.searchAuthor(diary, author);
    if (view.printEntries(entries))
      return input.readInt("Write the number of the diary you want to pick (-1 for none): ");
    else return -1;

  }

  private int getEntriesDate(EntryManager entryManager) {
    HashMap<Integer, Entry> diary = entryManager.getDiary();
    LocalDate localDate = input.readDate();
    HashMap<Integer, Entry> entries = sort.searchDate(diary, localDate);
    if (view.printEntries(entries))
      return input.readInt("Write the number of the diary you want to pick (-1 for none): ");
    else return -1;
  }

  private int getEntriesPeriod(EntryManager entryManager) {
    HashMap<Integer, Entry> diary = entryManager.getDiary();
    LocalDate date1 = input.readDate();
    LocalDate date2 = input.readDate();
    HashMap<Integer, Entry> entries = sort.searchPeriod(diary, date1, date2);
    if (view.printEntries(entries))
      return input.readInt("Write the number of the diary you want to pick (-1 for none): ");
    else return -1;
  }


}
