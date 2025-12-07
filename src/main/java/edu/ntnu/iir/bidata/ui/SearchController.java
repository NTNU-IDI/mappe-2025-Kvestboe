package edu.ntnu.iir.bidata.ui;

import static edu.ntnu.iir.bidata.ui.ConsoleView.*;
import static edu.ntnu.iir.bidata.utils.EntrySort.*;

import edu.ntnu.iir.bidata.model.Author;
import edu.ntnu.iir.bidata.model.Entry;
import edu.ntnu.iir.bidata.storage.AuthorManager;
import edu.ntnu.iir.bidata.storage.EntryManager;
import java.time.LocalDate;
import java.util.HashMap;

public class SearchController {

  AuthorController authorController;
  ConsoleInput input;
  EntryController entryController;

  public SearchController(AuthorController authorController,ConsoleInput input, EntryController entryController) {
    this.authorController = authorController;
    this.input = input;
    this.entryController = entryController;
  }

  public void searchEntries(EntryManager entryManager, AuthorManager authorManager) {
    boolean running = true;
    boolean valid = true;
    while(running) {
      promptForSearchAction();
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
    printEntries(entries);
    return input.readInt("Write the number of the diary you want to pick (-1 for none): ");
  }

  private int getEntriesTitle(EntryManager entryManager) {
    HashMap<Integer, Entry> diary = entryManager.getDiary();
    String title = input.readLine("Write in the title you want to search by: ");
    HashMap<Integer, Entry> entries = searchTitle(diary, title);
    if (!entries.isEmpty()) {
      printEntries(entries);
      return input.readInt("Write the number of the diary you want to pick (-1 for none): ");
    } else return -1;

  }

  private int getEntriesTag(EntryManager entryManager) {
    HashMap<Integer, Entry> diary = entryManager.getDiary();
    String tag = input.readLine("Write in the tag you want to search by: ");
    HashMap<Integer, Entry> entries = searchTag(diary, tag);

    if (printEntries(entries))
      return input.readInt("Write the number of the diary you want to pick (-1 for none): ");
    else return -1;
  }

  private int getEntriesAuthor(EntryManager entryManager, AuthorManager authorManager) {
    HashMap<Integer, Entry> diary = entryManager.getDiary();
    Author author = authorController.pickAuthor(authorManager);
    HashMap<Integer, Entry> entries = searchAuthor(diary, author);
    if (printEntries(entries))
      return input.readInt("Write the number of the diary you want to pick (-1 for none): ");
    else return -1;

  }

  private int getEntriesDate(EntryManager entryManager) {
    HashMap<Integer, Entry> diary = entryManager.getDiary();
    LocalDate localDate = input.readDate();
    HashMap<Integer, Entry> entries = searchDate(diary, localDate);
    if (printEntries(entries))
      return input.readInt("Write the number of the diary you want to pick (-1 for none): ");
    else return -1;
  }

  private int getEntriesPeriod(EntryManager entryManager) {
    HashMap<Integer, Entry> diary = entryManager.getDiary();
    LocalDate date1 = input.readDate();
    LocalDate date2 = input.readDate();
    HashMap<Integer, Entry> entries = searchPeriod(diary, date1, date2);
    if (printEntries(entries))
      return input.readInt("Write the number of the diary you want to pick (-1 for none): ");
    else return -1;
  }


}
