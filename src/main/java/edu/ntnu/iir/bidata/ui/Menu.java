package edu.ntnu.iir.bidata.ui;

import edu.ntnu.iir.bidata.model.User;
import edu.ntnu.iir.bidata.storage.DiaryManager;
import edu.ntnu.iir.bidata.storage.UserManager;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    /*
    this class is mostly output, and creating the objects of each class needed
    this is the class that will communicate with the Main class
     */

    // old
    // print user options
    public void userMenu(UserManager userManager) {
        ArrayList<String> userNames = userManager.getUsers();
        System.out.println("Change user:");
        for (String name: userNames) {
            System.out.println(name+".");
        }
        System.out.println("new: new user.");
    }

    // need to rework
    // print menu options
    public void showMenu() {
        System.out.println("Select your option:");
        System.out.println("new: new entry.");
        System.out.println("prior: view prior entries.");
        System.out.println("user: change user.");
        System.out.println("exit: exit.");
    }

    // old
    // print entry options
    public void addEntry(Scanner input, DiaryManager entries) {
        entries.addEntry(input);
    }

    //old
    // option for selecting prior entries
    public void priorEntries(Scanner input, DiaryManager entries) {
        entries.priorEntries(input);
    }


    // this is part of the new code
    Scanner input = new Scanner(System.in);

    UserManager userManager = new UserManager();
    DiaryManager diaryManager = new DiaryManager();

    IO io = new IO();

    User user =

    public void initialize() {

    }

    public void start() {
        boolean runProgram = true;

        while (runProgram) {
            showMenu();
            String menuChoice = input.nextLine();

            switch(menuChoice) {
                case "new" -> io.newDiary();

                case "prior" -> io.priorDiaries();

                case "user" -> io.userSettings(userManager);

                case "exit" -> runProgram = false;
            }
        }
    }
}
