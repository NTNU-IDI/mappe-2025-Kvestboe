package edu.ntnu.iir.bidata.ui;

import edu.ntnu.iir.bidata.model.Author;
import edu.ntnu.iir.bidata.model.Entry;
import edu.ntnu.iir.bidata.model.Statistic;
import edu.ntnu.iir.bidata.storage.AuthorManager;
import edu.ntnu.iir.bidata.storage.EntryManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class holds the print statements in the application.
 *
 * <p>This class handles all printing and options, which calls for better cohesion.
 * The functions are also static which leads to better coupling,
 * and is possible since it doesn't need much context.</p>
 */
public class ConsoleView {

  /**
   * Print the main menu options based on whether the diary is empty.
   *
   * @param entryManager the EntryManager providing access to the diary
   *
   */
  static void promptForMenuAction(EntryManager entryManager) {
    if (entryManager.getDiary().isEmpty()) {
      System.out.println("Select your option:");
      System.out.println("new: new entry.");
      System.out.println("author: change author.");
      System.out.println("exit: exit.");
      System.out.print("> ");

    } else {
      System.out.println("Select your option.");
      System.out.println("new: new entry.");
      System.out.println("prior: view prior entries.");
      System.out.println("author: change author.");
      System.out.println("stat: show the statistics of the diary.");
      System.out.println("exit: exit.");
      System.out.print("> ");

    }
  }

  /**
   * Print the edit menu for a single entry showing editable fields and actions.
   *
   * @param entry the Entry whose fields are shown for editing
   */
  static void promptForEditAction(Entry entry) {
    System.out.println("Is there anything you wish to edit?");
    System.out.println("author: " + entry.getAuthor().getName());
    System.out.println("title: " + entry.getTitle());
    System.out.println("tags: " + entry.getTagsString());
    System.out.println("date: " + entry.getDateString());
    System.out.println("content: view the content or edit it");
    System.out.println("delete: delete the diary entry");
    System.out.println("none: go back");
    System.out.print("> ");

  }

  /**
   * Print the content submenu offering read, write, and append actions.
   * The method lists available content operations and prints a prompt for user input.
   */
  static void promptForContentAction() {
    System.out.println("What do you wish to do?");
    System.out.println("read: read the content of the diary");
    System.out.println("write: rewrite the content of the diary");
    System.out.println("add: add to the already existing content");
    System.out.println("none: exit the content menu");
    System.out.print("> ");

  }

  /**
   * Print the tag management submenu offering add and remove actions.
   */
  static void promptForTagAction() {

    System.out.println("What do you wish to do with the tags.");
    System.out.println("add: add new tags");
    System.out.println("remove: remove tags");
    System.out.println("write anything to go back");
    System.out.print("> ");
  }

  /**
   * Print the search submenu describing available search criteria.
   */
  static void promptForSearchAction() {
    System.out.println("What do you wish to search the diaries by?");
    System.out.println("all: print all entries");
    System.out.println("title: search for a title");
    System.out.println("tag: search by tags");
    System.out.println("author: search for authors");
    System.out.println("date: search by a date");
    System.out.println("period: search by period");
    System.out.println("none: exit sorting menu");
    System.out.print("> ");

  }

  /**
   * Prompt the user for a yes or no confirmation.
   * The prompt expects the user to answer "yes" or "no".
   */
  static void promptConfirmationAction() {
    System.out.print("Are you sure (yes/no): ");
  }

  /**
   * Prompt the user to enter date in format YYYY-MM-DD.
   */
  static void promptMakeDate() {
    System.out.print("Write in the date in the format \"YYYY-MM-DD\": ");
  }

  /**
   * Print a list of entries with their integer keys and titles.
   * If the provided map is empty, prints a message and returns false.
   * Else prints each key and title and returns true.
   *
   * @param entries a map from integer keys to Entry objects
   * @return true if at least one entry was printed, false if the map was empty
   */
  static boolean printEntries(HashMap<Integer, Entry> entries) {
    if (entries.isEmpty()) {
      System.out.println("No entries found.");
      return false;
    } else {
      for (int key : entries.keySet()) {
        System.out.println(key + ": " + entries.get(key).getTitle());
      }
      return true;

    }

  }

  /**
   * Print an invalid choice message.
   */
  static void printInvalidAction() {
    System.out.println("Invalid choice.");
  }

  /**
   * Print the given prompt.
   *
   * @param prompt prompt is the text to be printed
   */
  static void printLine(String prompt) {
    System.out.print(prompt);
  }

  /**
   * Print the list of authors highlighting the active author.
   * Also prints options to create a new user or exit the user manager.
   *
   * @param authorManager the AuthorManager providing the list of author names
   * @param activeAuthor the currently active Author to highlight
   */
  static void promptAuthorOptions(AuthorManager authorManager, Author activeAuthor) {
    final String Reset = "\u001B[0m";
    final String Green = "\u001B[32m";

    for (String authorName : authorManager.getAuthors()) {
      if (authorName.equals(activeAuthor.getName())) {
        System.out.println(Green + authorName + Reset);
      } else {
        System.out.println(authorName);
      }
    }
    System.out.println("new: make new user");
    System.out.println("none: exit user manager");
    System.out.print("> ");
  }

  /**
   * Print the available authors for selection and a prompt to exit the picker.
   *
   * @param authorManager the AuthorManager providing the list of author names
   */
  static void promptAuthorPicker(AuthorManager authorManager) {
    authorManager.getAuthors().forEach(System.out::println);
    System.out.println("none: exit author picker");
    System.out.print("> ");
  }

  /**
   * Print diary statistics including entries this month, top tags, and author counts.
   *
   * @param entryManager the EntryManager used to obtain statistics
   */
  static void printStatistics(EntryManager entryManager) {
    Statistic stat = entryManager.getStatistic();
    System.out.println("Entries this month: " + stat.getEntriesThisMonth());
    System.out.println("All tag count: ");

    List<Map.Entry<String, Integer>> topTags = stat.getTagCount().entrySet().stream()
        .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
        .limit(3)
        .toList();
    for (Map.Entry<String, Integer> entry : topTags) {
      System.out.println(entry.getKey() + ": " + entry.getValue());
    }
    System.out.println("All author count: ");
    for (String authorName : stat.getAuthorCount().keySet()) {
      System.out.println(authorName + ": " + stat.getAuthorCount().get(authorName));
    }

  }

}
