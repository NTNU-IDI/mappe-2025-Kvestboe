package edu.ntnu.iir.bidata.ui;

import edu.ntnu.iir.bidata.model.Author;
import edu.ntnu.iir.bidata.storage.EntryManager;
import edu.ntnu.iir.bidata.storage.AuthorManager;

import java.util.Scanner;

public class Menu {
    /*
    this class is mostly output, and creating the objects of each class needed
    this is the class that will communicate with the Main class
     */

    // need to rework
    // print menu options
    public void showMenu() {
        System.out.println("Select your option:");
        System.out.println("new: new entry.");
        System.out.println("prior: view prior entries.");
        System.out.println("user: change user.");
        System.out.println("exit: exit.");
    }

    // this is part of the new code
    Scanner input = new Scanner(System.in);

    AuthorManager authorManager = new AuthorManager();
    EntryManager entryManager = new EntryManager();

    IO io = new IO();

    Author author = io.addUser(authorManager);

    public void initialize() {

    }

    public void start() {
        boolean runProgram = true;

        while (runProgram) {
            showMenu();
            String menuChoice = input.nextLine();

            switch(menuChoice) {
                case "new" -> io.newDiary(entryManager, author, authorManager);

                case "prior" -> io.priorDiaries(entryManager, authorManager);

                case "user" -> checkUser();

                case "exit" -> runProgram = false;
            }
        }
    }
    private void checkUser() {
        Author newAuthor = io.userSettings(authorManager, author);
        if (newAuthor != null) {
            author = newAuthor;
        }
    }
}
