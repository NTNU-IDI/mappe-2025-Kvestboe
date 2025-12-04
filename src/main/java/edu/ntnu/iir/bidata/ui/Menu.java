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
        if (entryManager.allDiaries().isEmpty()) {
            System.out.println("Select your option:");
            System.out.println("new: new entry.");
            System.out.println("author: change author.");
            System.out.println("exit: exit.");

        } else {
            System.out.println("Select your option:");
            System.out.println("new: new entry.");
            System.out.println("prior: view prior entries.");
            System.out.println("author: change author.");
            System.out.println("exit: exit.");

        }
    }

    // this is part of the new code
    Scanner input = null;

    AuthorManager authorManager = null;
    EntryManager entryManager = null;
    Author author = null;

    IO io = null;


    public void initialize() {
        input = new Scanner(System.in);
        authorManager = new AuthorManager();
        entryManager = new EntryManager();

        io = new IO();
        author = io.addUser(authorManager);

    }

    public void start() {

        if (input != null || authorManager != null || entryManager != null || io != null || author != null) {
            boolean runProgram = true;

            while (runProgram) {
                showMenu();
                String menuChoice = input.nextLine();

                switch(menuChoice) {
                    case "new" -> io.newDiary(entryManager, author, authorManager);

                    case "prior" -> io.priorDiaries(entryManager, author, authorManager);

                    case "author" -> checkUser();

                    case "exit" -> runProgram = false;
                }
            }

        } else {
            initialize();
        }

    }
    private void checkUser() {
        Author newAuthor = io.userSettings(authorManager, author);
        if (newAuthor != null) {
            author = newAuthor;
        }
    }
}
