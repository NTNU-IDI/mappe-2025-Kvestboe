package edu.ntnu.iir.bidata.ui;

import static edu.ntnu.iir.bidata.ui.ConsoleView.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class is responsible for taking all the input.
 */
public class ConsoleInput {

  /**
   * Input so the user can enter values.
   */
  Scanner input;

  /**
   * Constructor for the input of the diary.
   *
   * @param scanner scanner is for the input
   */
  public ConsoleInput(Scanner scanner) {
    input = scanner;

  }

  /**
   * This method will only ask for input without any prompt.
   *
   * @return the string that user writes in
   */
  public String read() {
    return input.nextLine();
  }

  /**
   * Prints the given prompt and reads a single line of text from the input.
   *
   * @param prompt the text to display to the user before reading input
   * @return the line entered by the user
   */
  public String readLine(String prompt) {
    printLine(prompt);
    return input.nextLine();
  }

  /**
   * Prints the given prompt and attempts to read an integer from the input.
   *
   * @param prompt the prompt to be printed
   * @return the number the user wrote, unless it's a string
   */
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

  /**
   * Prompts the user to enter a date and parses it into a java.time.LocalDate.
   *
   * @return the parsed LocalDate, or null if parsing failed
   */
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

  /**
   * Prompts the user for a confirmation and returns the result as a boolean.
   *
   * @return true if the user confirmed with "yes", false if the user answered "no"
   */
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

  /**
   * Prints the given prompt and reads a whitespace-separated list of tags from the input.
   *
   * @param prompt the text to display to the user before reading tags
   * @return an ArrayList of unique tags in the order they appeared
   */
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

  /**
   * Reads multiple lines of input until a line containing "done" is entered.
   *
   * @return the multiline content entered by the user, with newline characters
   */
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
