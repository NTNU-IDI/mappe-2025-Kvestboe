package edu.ntnu.iir.bidata.ui;

import edu.ntnu.iir.bidata.model.User;
import edu.ntnu.iir.bidata.storage.DiaryManager;
import edu.ntnu.iir.bidata.storage.UserManager;

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

    UserManager userManager = new UserManager();
    DiaryManager diaryManager = new DiaryManager();

    IO io = new IO();

    User user = io.addUser(userManager);

    public void initialize() {

    }

    public void start() {
        boolean runProgram = true;

        while (runProgram) {
            showMenu();
            String menuChoice = input.nextLine();

            switch(menuChoice) {
                case "new" -> io.newDiary(diaryManager);

                case "prior" -> io.priorDiaries(diaryManager);

                case "user" -> checkUser();

                case "exit" -> runProgram = false;
            }
        }
    }
    private void checkUser() {
        User newUser = io.userSettings(userManager, user);
        if (newUser != null) {
            user = newUser;
        }
    }
}
