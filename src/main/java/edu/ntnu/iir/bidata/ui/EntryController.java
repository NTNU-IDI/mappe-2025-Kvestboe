package edu.ntnu.iir.bidata.ui;

import static edu.ntnu.iir.bidata.ui.ConsoleView.printInvalidAction;
import static edu.ntnu.iir.bidata.ui.ConsoleView.printLine;
import static edu.ntnu.iir.bidata.ui.ConsoleView.promptForContentAction;
import static edu.ntnu.iir.bidata.ui.ConsoleView.promptForEditAction;
import static edu.ntnu.iir.bidata.ui.ConsoleView.promptForTagAction;

import edu.ntnu.iir.bidata.model.Author;
import edu.ntnu.iir.bidata.model.Entry;
import edu.ntnu.iir.bidata.storage.AuthorManager;
import edu.ntnu.iir.bidata.storage.EntryManager;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Controller responsible for creating and editing diary entries.
 *
 * <p>Controls the flow of the program when the user wishes to do anything with the entries.
 * Uses AuthorController for author selection and ConsoleInput for user interaction.</p>
 */
public class EntryController {

  AuthorController authorController;
  ConsoleInput input;

  /**
   * Constructor for the entry controller.
   *
   * @param authorController controller used to manage and pick authors
   * @param input console input helper used to read user input
   */
  public EntryController(AuthorController authorController, ConsoleInput input) {
    this.authorController =  authorController;
    this.input = input;
  }

  /**
   * Create a new diary entry by prompting the user for title, tags and content,
   * add it to the entry manager and prompt to edit diary to make changes.
   *
   * @param entryManager manager that stores entries
   * @param author the author to assign to the new entry
   * @param authorManager manager that stores authors
   */
  public void createEntry(EntryManager entryManager, Author author, AuthorManager authorManager) {
    String title = input.readLine("What is the title of the entry you want to create: ");
    ArrayList<String> tags = input.readTags("Write in tags with a space between: ");
    String content = input.readMultiline();

    Entry diary = new Entry(author, title, tags, content);
    int key = entryManager.addEntry(diary);

    editDiary(key, authorManager, entryManager);

  }

  /**
   * Open the edit loop for an existing entry identified by key.
   *
   * <p>Updated to entry manager when the user finishes editing.</p>
   *
   * @param key the integer key identifying the entry in the entry manager
   * @param authorManager manager that stores authors
   * @param entryManager manager that stores entries
   */
  public void editDiary(int key, AuthorManager authorManager, EntryManager entryManager) {
    Entry entry = entryManager.getEntry(key).copy();
    boolean running = true;

    while (running) {
      String choice = editMenu(entry);
      switch (choice) {
        case "author" -> editAuthor(entry, authorManager);
        case "title" -> editTitle(entry);
        case "tags" -> editTags(entry);
        case "date" -> editDate(entry);
        case "content" -> editContent(entry);
        case "delete" -> {
          deleteEntry(entry, entryManager);
          running = false;
        }
        case "none" -> running = false;
        default -> printInvalidAction();
      }
    }
    entryManager.updateEntry(key, entry);

  }

  /**
   * Print the edit options and read the user's choice.
   *
   * @param entry entry to be edited
   * @return the edit choice of the user
   */
  private String editMenu(Entry entry) {
    promptForEditAction(entry);
    return input.read();

  }

  /**
   * Prompt the user to delete entry.
   *
   * @param entry entry to be deleted
   * @param entryManager entryManager that manages stored entries
   */
  private void deleteEntry(Entry entry, EntryManager entryManager) {
    boolean choice = input.readConfirmation();

    if (choice) {
      entryManager.deleteEntry(entry);
    } else {
      printLine("Entry was not deleted.");
    }

  }

  /**
   * Prompt the user to change the author of the entry.
   *
   * @param entry entry that will be edited
   * @param authorManager authorManager that stores available authors
   */
  private void editAuthor(Entry entry, AuthorManager authorManager) {
    Author author = authorController.authorOptions(authorManager, entry.getAuthor());
    if (author != null) {
      entry.setAuthor(author);
      printLine("Author was updated.\n");
    } else {
      printLine("Author was not updated.\n");
    }

  }

  /**
   * Prompt the user to edit the title of the entry.
   *
   * @param entry entry to be edited
   */
  private void editTitle(Entry entry) {
    String newTitle = input.readLine("New title, \"none\" to not change: ");
    if (!newTitle.equals("none")) {
      entry.setTitle(newTitle);
      printLine("Changed the title.\n");
    } else {
      printLine("Did not change title.\n");
    }

  }

  /**
   * Prompt the user to add or remove tags for the entry.
   *
   * @param entry entry to be edited
   */
  private void editTags(Entry entry) {
    promptForTagAction();
    String choice = input.read();
    switch (choice) {
      case "add" -> addTags(entry);
      case "remove" -> removeTags(entry);
      default -> printLine("No option matches \"" + choice + "\", going back.");
    }

  }

  /**
   * Prompt the user to enter a new date and update old one.
   *
   * <p>If the input cannot be parsed, the date is not changed.</p>
   *
   * @param entry entry to be edited
   */
  private void editDate(Entry entry) {
    LocalDate newDate = input.readDate();
    if (newDate != null) {
      entry.setDate(newDate);
    } else {
      printLine("Did not change date.\n");
    }

  }

  /**
   * Edit-menu for the content of an entry.
   *
   * @param entry entry to be edited
   */
  private void editContent(Entry entry) {
    boolean running = true;
    while (running) {
      String choice = contentMenu();
      switch (choice) {
        case "read" -> printLine(entry.getContent());
        case "write" -> writeContent(entry);
        case "add" -> addContent(entry);
        case "none" -> running = false;
        default  -> printInvalidAction();

      }
    }

  }

  /**
   * Print the content editing choices and return the user choice.
   *
   * @return the user's content menu choice
   */
  private String contentMenu() {
    promptForContentAction();
    return input.read();
  }

  /**
   * Replace the entry content with new multiline content from the user.
   *
   * @param entry entry to be edited
   */
  private void writeContent(Entry entry) {
    String content = input.readMultiline();
    entry.setContent(content);

  }

  /**
   * Append multiline content from the user to the existing entry content.
   *
   * @param entry entry to be edited
   */
  private void addContent(Entry entry) {
    String content = entry.getContent();
    content += input.readMultiline();
    entry.setContent(content);
  }

  /**
   * Read tags from the user and replace.
   *
   * @param entry entry to be edited
   */
  private void addTags(Entry entry) {
    ArrayList<String> tagList = input.readTags("Write the tags you wish to add (space between): ");
    entry.getTags().addAll(tagList);
  }

  /**
   * Remove tags from the entry.
   *
   * <p>Non-existing tags are written to the console.</p>
   *
   * @param entry entry to be edited
   */
  private void removeTags(Entry entry) {
    ArrayList<String> removeTags =
        input.readTags("Write the tags you wish to remove (space between): ");
    ArrayList<String> tags = entry.getTags();

    for (String tag : removeTags) {
      if (!tags.contains(tag)) {
        input.readLine("No tag \"" + tag + "\" found.");
      }
    }
    tags.removeAll(removeTags);

    entry.setTags(tags);

  }

}


