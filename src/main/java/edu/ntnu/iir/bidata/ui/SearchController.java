package edu.ntnu.iir.bidata.ui;

import edu.ntnu.iir.bidata.model.Author;
import edu.ntnu.iir.bidata.model.Entry;
import edu.ntnu.iir.bidata.storage.AuthorManager;
import edu.ntnu.iir.bidata.storage.EntryManager;

public class SearchController {

  ConsoleInput input;
  ConsoleView view;

  public SearchController(ConsoleInput input,  ConsoleView view) {
    this.input = input;
    this.view = view;
  }

  public void searchEntries(EntryManager entryManager, AuthorManager authorManager, Author author) {
    boolean running = true;
    boolean valid = true;
    while(running) {
      String choice = priorEntriesMenu();
      Entry entry = null;
      switch (choice) {
        case "all" -> entry = getAllEntries(entryManager);
        case "title" -> entry = getEntriesTitle(entryManager);
        case "tag" -> entry = getEntriesTag(entryManager);
        case "author" -> entry = getEntriesAuthor(entryManager, author, authorManager);
        case "date" -> entry = getEntriesDate(entryManager);
        case "period" -> entry = getEntriesPeriod(entryManager);
        case "none" -> running = false;
        default -> valid = false;
      }
      if (running && valid) {
        if (entry != null) {
          editDiary(entry, authorManager, entryManager);
        }
      }
    }
  }





}
