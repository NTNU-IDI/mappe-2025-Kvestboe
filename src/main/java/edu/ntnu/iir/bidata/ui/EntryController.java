package edu.ntnu.iir.bidata.ui;

import edu.ntnu.iir.bidata.model.Author;
import edu.ntnu.iir.bidata.model.Entry;
import edu.ntnu.iir.bidata.storage.AuthorManager;
import edu.ntnu.iir.bidata.storage.EntryManager;
import java.util.ArrayList;

public class EntryController {

  ConsoleView view;
  ConsoleInput input;

  public EntryController(ConsoleView view, ConsoleInput input) {
    this.view = view;
    this.input = input;
  }

  public void createEntry(EntryManager entryManager, Author author, AuthorManager authorManager) {
    String title = input.readLine("What is the title of the entry you want to create?");

    ArrayList<String> tags = input.readTags("Write in tags with a space between:");

    String content = input.readMultiline("Write in the content of the diary, "
        + "write done when finished: ");

    Entry diary= new Entry(author, title, tags, content);
    int entry = entryManager.addEntry(diary);

    editDiary(entryManager.getEntry(entry), authorManager, entryManager);

  }


  public void editDiary(Entry entry, AuthorManager authorManager, EntryManager entryManager) {
    boolean running = true;

    while (running) {
      String choice = editMenu(entry);
      switch (choice) {
        case "author" -> editUser(entry, authorManager);
        case "title" -> editTitle(entry);
        case "tags" -> editTags(entry);
        case "date" -> editDate(entry);
        case "content" -> editContent(entry);
        case "delete" -> {
          deleteEntry(entry, entryManager);
          running = false;
        }
        case "none" -> running = false;
        default -> System.out.println("Invalid choice.");
      }
    }
  }
}


