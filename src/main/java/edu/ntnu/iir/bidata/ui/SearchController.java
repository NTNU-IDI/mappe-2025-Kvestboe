package edu.ntnu.iir.bidata.ui;

import static edu.ntnu.iir.bidata.ui.ConsoleView.*;
import static edu.ntnu.iir.bidata.utils.EntrySort.*;

import edu.ntnu.iir.bidata.model.Author;
import edu.ntnu.iir.bidata.model.Entry;
import edu.ntnu.iir.bidata.storage.AuthorManager;
import edu.ntnu.iir.bidata.storage.EntryManager;
import java.time.LocalDate;
import java.util.HashMap;

/**
 * Controller responsible for searching and selecting diary entries.
 * Uses AuthorController to pick authors, ConsoleInput for user interaction,
 * and EntryController to open the edit flow for a selected entry.
 */
public class SearchController {

  AuthorController authorController;
  ConsoleInput input;
  EntryController entryController;

  /**
   * Constructor for the search controller class.
   *
   * @param authorController controller used to pick authors
   * @param input console input helper used to read user input
   * @param entryController controller used to open the entry edit flow
   */
  public SearchController(AuthorController authorController, ConsoleInput input, EntryController entryController) {
    this.authorController = authorController;
    this.input = input;
    this.entryController = entryController;
  }

  /**
   * This method is the flow of the search function of the diary.
   *
   * @param entryManager manager that stores entries
   * @param authorManager manager that stores authors
   */
  public void searchEntries(EntryManager entryManager, AuthorManager authorManager) {
    boolean running = true;
    boolean valid = true;
    while (running) {
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

  /**
   * Print all entries and prompt the user to pick one.
   *
   * @param entryManager manager that stores entries
   * @return the selected entry key, or -1 if none
   */
  private int getAllEntries(EntryManager entryManager) {
    HashMap<Integer, Entry> entries = entryManager.getDiary();
    printEntries(entries);
    return input.readInt("Write the number of the diary you want to pick (-1 for none): ");
  }

  /**
   * Search entries by title, make user pick one.
   *
   * @param entryManager manager that stores entries
   * @return the selected entry key, or -1 if no matches or none selected
   */
  private int getEntriesTitle(EntryManager entryManager) {
    HashMap<Integer, Entry> diary = entryManager.getDiary();
    String title = input.readLine("Write in the title you want to search by: ");
    HashMap<Integer, Entry> entries = searchTitle(diary, title);
    if (!entries.isEmpty()) {
      printEntries(entries);
      return input.readInt("Write the number of the diary you want to pick (-1 for none): ");
    } else return -1;
  }

  /**
   * Search entries by tag, make user pick one.
   *
   * @param entryManager manager that stores entries
   * @return the selected entry key, or -1 if no matches or none selected
   */
  private int getEntriesTag(EntryManager entryManager) {
    HashMap<Integer, Entry> diary = entryManager.getDiary();
    String tag = input.readLine("Write in the tag you want to search by: ");
    HashMap<Integer, Entry> entries = searchTag(diary, tag);

    if (printEntries(entries))
      return input.readInt("Write the number of the diary you want to pick (-1 for none): ");
    else return -1;
  }

  /**
   * Let the user pick an author, search entries by that author and pick one.
   *
   * @param entryManager manager that stores entries
   * @param authorManager manager that stores authors
   * @return the selected entry key, or -1 if no matches or none selected
   */
  private int getEntriesAuthor(EntryManager entryManager, AuthorManager authorManager) {
    HashMap<Integer, Entry> diary = entryManager.getDiary();
    Author author = authorController.pickAuthor(authorManager);
    HashMap<Integer, Entry> entries = searchAuthor(diary, author);
    if (printEntries(entries))
      return input.readInt("Write the number of the diary you want to pick (-1 for none): ");
    else return -1;
  }

  /**
   * Prompt the user for a date, search entries on that date and pick one.
   *
   * @param entryManager manager that stores entries
   * @return the selected entry key, or -1 if no matches or none selected
   */
  private int getEntriesDate(EntryManager entryManager) {
    HashMap<Integer, Entry> diary = entryManager.getDiary();
    LocalDate localDate = input.readDate();
    HashMap<Integer, Entry> entries = searchDate(diary, localDate);
    if (printEntries(entries))
      return input.readInt("Write the number of the diary you want to pick (-1 for none): ");
    else return -1;
  }

  /**
   * Prompt the user for a period and search entries in it, pick one.
   *
   * @param entryManager manager that stores entries
   * @return the selected entry key, or -1 if no matches or none selected
   */
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