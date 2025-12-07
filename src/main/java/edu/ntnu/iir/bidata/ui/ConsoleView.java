package edu.ntnu.iir.bidata.ui;

import edu.ntnu.iir.bidata.model.Entry;
import edu.ntnu.iir.bidata.storage.EntryManager;
import java.util.HashMap;

public class ConsoleView {

  /**
   * This method will print the main menu.
   */
  public void promptForMenuAction(EntryManager entryManager) {
    if (entryManager.getEntries().isEmpty()) {
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

  public void promptForEditAction(Entry entry) {
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

  public void promptForContentAction() {
    System.out.println("What do you wish to do?");
    System.out.println("read: read the content of the diary");
    System.out.println("write: rewrite the content of the diary");
    System.out.println("add: add to the already existing content");
    System.out.println("none: exit the content menu");
    System.out.print("> ");

  }

  public void promptForSearchAction() {
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

  public void printEntries(HashMap<Integer, Entry> entries) {
    for (int key : entries.keySet()) {
      System.out.println(key + ": " + entries.get(key));
    }

  }

  public void printInvalidAction() {
    System.out.println("Invalid choice.");
  }
}
