package edu.ntnu.iir.bidata.ui;

import edu.ntnu.iir.bidata.model.Author;
import edu.ntnu.iir.bidata.model.Entry;
import edu.ntnu.iir.bidata.storage.AuthorManager;
import edu.ntnu.iir.bidata.storage.EntryManager;
import java.time.LocalDate;
import java.util.ArrayList;

public class EntryController {

  AuthorController authorController;
  ConsoleView view;
  ConsoleInput input;

  public EntryController(AuthorController authorController, ConsoleView view, ConsoleInput input) {
    this.authorController =  authorController;
    this.view = view;
    this.input = input;
  }

  public void createEntry(EntryManager entryManager, Author author, AuthorManager authorManager) {
    String title = input.readLine("What is the title of the entry you want to create: ");

    ArrayList<String> tags = input.readTags("Write in tags with a space between: ");

    String content = input.readMultiline();

    Entry diary= new Entry(author, title, tags, content);
    int entry = entryManager.addEntry(diary);

    editDiary(entryManager.getEntry(entry), authorManager, entryManager);

  }


  public void editDiary(Entry entry, AuthorManager authorManager, EntryManager entryManager) {
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
        default -> view.printInvalidAction();
      }
    }
  }

  /**
   * This method will print the edit options.
   *
   * @param entry entry to be edited
   * @return the choice of the user that they wish to edit
   */
  private String editMenu(Entry entry) {
    view.promptForEditAction(entry);
    return input.read();

  }

  /**
   * This method will prompt the user to delete the entry.
   *
   * @param entry        entry to be deleted
   * @param entryManager entryManager is the register of entries
   */
  private void deleteEntry(Entry entry, EntryManager entryManager) {
    boolean choice = input.readConfirmation();

    if (choice) {
      entryManager.deleteEntry(entry);
    } else {
      view.printLine("Entry was not deleted.");
    }

  }

  /**
   * This method will prompt the user to change the author.
   *
   * @param entry         entry that will be edited
   * @param authorManager authorManager is the register of authors
   */
  private void editAuthor(Entry entry, AuthorManager authorManager) {
    Author author = authorController.authorOptions(authorManager, entry.getAuthor());
    if (author != null) {
      entry.setAuthor(author);
      view.printLine("Author was updated.\n");
    } else {
      view.printLine("Author was not updated.\n");
    }

  }

  /**
   * This method will prompt the user to edit the title.
   *
   * @param entry entry is the entry to be edited
   */
  private void editTitle(Entry entry) {
    view.promptForTagAction(entry);
    String newTitle = input.read();
    if (!newTitle.equals("none")) {
      entry.setTitle(newTitle);
      view.printLine("Changed the title.\n");
    } else {
      view.printLine("Did not change title.\n");
    }

  }

  /**
   * This method will prompt the user to edit the tags.
   *
   * @param entry entry to be edited
   */
  private void editTags(Entry entry) {
    view.promptForTagAction(entry);
    String choice = input.read();
    switch (choice) {
      case "add" -> addTags(entry);
      case "remove" -> removeTags(entry);
      default -> view.printLine("No option matches \"" + choice + "\", going back.");
    }

  }

  /**
   * This method will prompt the user to edit the date.
   *
   * @param entry entry to be edited
   */
  private void editDate(Entry entry) {
    view.promptForDateAction(entry);
    LocalDate newDate = input.readDate();
    if (newDate != null) {
      entry.setDate(newDate);
    } else {
      view.printLine("Did not change date.\n");
    }

  }


  /**
   * This method is the logic for editing the content an entry.
   *
   * @param entry entry to be edited
   */
  private void editContent(Entry entry) {
    boolean running = true;
    while (running) {
      String choice = contentMenu();
      switch (choice) {
        case "read" -> view.printLine(entry.getContent());
        case "write" -> writeContent(entry);
        case "add" -> addContent(entry);
        case "none" -> running = false;
        default  -> view.printInvalidAction();

      }
    }

  }

  /**
   * This method will print the choices to edit the content of an entry, and return the choice of
   * the user.
   *
   * @return the choice of the author
   */
  private String contentMenu() {
    view.promptForContentAction();
    return input.read();
  }

  /**
   * This method change all the content in the entry.
   *
   * @param entry entry to be edited
   */
  private void writeContent(Entry entry) {
    String content = input.readMultiline();
    entry.setContent(content);

  }

  /**
   * This method will add content to already existing content of entry.
   *
   * @param entry entry to be edited
   */
  private void addContent(Entry entry) {
    String content = entry.getContent();
    content += input.readMultiline();
    entry.setContent(content);
  }

  /**
   * This method will add tags to an entry.
   *
   * @param entry entry to be edited
   */
  private void addTags(Entry entry) {
    ArrayList<String> tagList = input.readTags("Write the tags you wish to add (space between): ");
    entry.setTags(tagList);
  }

  /**
   * This method will remove tags of an entry.
   *
   * @param entry entry to be edited
   */
  private void removeTags(Entry entry) {
    ArrayList<String> removeTags = input.readTags("Write the tags you wish to remove (space between): ");
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


