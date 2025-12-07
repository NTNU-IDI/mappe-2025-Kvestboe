package edu.ntnu.iir.bidata.ui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleInput {
  Scanner input;
  ConsoleView view;

  public ConsoleInput(Scanner scanner, ConsoleView view) {
    input = scanner;
    this.view = view;

  }

  public String read() {
    return input.nextLine();
  }

  public String readLine(String prompt) {
    view.printLine(prompt);
    return input.nextLine();
  }

  public int readInt(String prompt) {
    view.printLine(prompt);
    int choice;
    try {
      choice = input.nextInt();
      input.nextLine();
    } catch (Exception e) {
      view.printInvalidAction();
      return -1;
    }
    return choice;
  }

  public LocalDate readDate() {
    view.promptMakeDate();
    LocalDate date;
    try {
      date = LocalDate.parse(input.nextLine());
    } catch (Exception e) {
      view.printInvalidAction();
      return null;
    }
    return date;
  }

  public boolean readConfirmation() {
    view.promptConfirmationAction();
    String confirmation = input.nextLine();
    if (confirmation.equals("yes")) {
      return true;
    } else if (confirmation.equals("no")) {
      return false;
    } else {
      view.printInvalidAction();
      return readConfirmation();
    }

  }

  public ArrayList<String> readTags(String prompt) {
    view.printLine(prompt);
    String tagString = input.nextLine();
    
    ArrayList<String> tagList = new ArrayList<>();
    String[] tagArray = tagString.split("\\s");

    for (String tag : tagArray) {
      if (!tagList.contains(tag)) {
        tagList.add(tag);
      }
    }
    return tagList;

  }

  public String readMultiline() {
    StringBuilder content = new StringBuilder();
    boolean done = false;

    view.printLine("Write in the content of the diary, write done when finished:\n");

    while (!done) {
      String line = input.nextLine();
      if (line.equals("done")) {
        done = true;

      } else {
        content.append(line).append("\n");

      }
    }
    return content.toString();
  }

}
