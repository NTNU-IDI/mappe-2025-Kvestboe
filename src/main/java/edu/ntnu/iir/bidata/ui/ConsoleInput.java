package edu.ntnu.iir.bidata.ui;

import static edu.ntnu.iir.bidata.ui.ConsoleView.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleInput {
  Scanner input;

  public ConsoleInput(Scanner scanner) {
    input = scanner;

  }

  public String read() {
    return input.nextLine();
  }

  public String readLine(String prompt) {
    printLine(prompt);
    return input.nextLine();
  }

  public int readInt(String prompt) {
    printLine(prompt);
    int choice;
    try {
      choice = input.nextInt();
      input.nextLine();
    } catch (Exception e) {
      printInvalidAction();
      return -1;
    }
    return choice;
  }

  public LocalDate readDate() {
    promptMakeDate();
    LocalDate date;
    try {
      date = LocalDate.parse(input.nextLine());
    } catch (Exception e) {
      printInvalidAction();
      return null;
    }
    return date;
  }

  public boolean readConfirmation() {
    promptConfirmationAction();
    String confirmation = input.nextLine();
    if (confirmation.equals("yes")) {
      return true;
    } else if (confirmation.equals("no")) {
      return false;
    } else {
      printInvalidAction();
      return readConfirmation();
    }

  }

  public ArrayList<String> readTags(String prompt) {
    printLine(prompt);
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

    printLine("Write in the content of the diary, write done when finished:\n");

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
