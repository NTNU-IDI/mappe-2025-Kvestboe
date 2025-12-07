package edu.ntnu.iir.bidata.ui;

import edu.ntnu.iir.bidata.model.Author;
import edu.ntnu.iir.bidata.model.Entry;
import edu.ntnu.iir.bidata.model.Statistic;
import edu.ntnu.iir.bidata.storage.AuthorManager;
import edu.ntnu.iir.bidata.storage.EntryManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConsoleView {

  /**
   * This method will print the main menu.
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

  static void promptForContentAction() {
    System.out.println("What do you wish to do?");
    System.out.println("read: read the content of the diary");
    System.out.println("write: rewrite the content of the diary");
    System.out.println("add: add to the already existing content");
    System.out.println("none: exit the content menu");
    System.out.print("> ");

  }

  static void promptForTagAction() {

    System.out.println("What do you wish to do with the tags.");
    System.out.println("add: add new tags");
    System.out.println("remove: remove tags");
    System.out.println("write anything to go back");
    System.out.print("> ");
  }

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

  static void promptConfirmationAction() {
    System.out.print("Are you sure (yes/no): ");
  }

  static void promptMakeDate() {
    System.out.print("Write in the date in the format \"YYYY-MM-DD\": ");
  }

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

  static void printInvalidAction() {
    System.out.println("Invalid choice.");
  }

  static void printLine(String prompt) {
    System.out.print(prompt);
  }

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

  static void promptAuthorPicker(AuthorManager authorManager) {
    authorManager.getAuthors().forEach(System.out::println);
    System.out.println("none: exit author picker");
    System.out.print("> ");
  }

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
