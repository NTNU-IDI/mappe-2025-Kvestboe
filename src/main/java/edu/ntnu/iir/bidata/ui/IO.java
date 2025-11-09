package edu.ntnu.iir.bidata.ui;

import edu.ntnu.iir.bidata.storage.UserManager;

import java.util.ArrayList;
import java.util.Scanner;

public class IO {
    /*
    this class is for handling input and output
    it handles the methods for the user choices
     */

    Scanner input = new Scanner(System.in);


    // this section will handle adding a new diary to the manager
    public void newDiary() {

    }

    // this section will handle the editing behind the diaries
    // most of those functions are in the diary class, which is bad practice
    public void editDiary() {

    }


    // this section is for viewing prior diaries
    // there will also be methods for sorting
    public void priorDiaries() {

    }

    // this section will handle the user
    public void userSettings(UserManager userManager) {
        boolean running = true;

        while (running) {
            String choice = userMenu(userManager);
            switch (choice) {
                case
            }
       }

    }

    private String userMenu(UserManager userManager) {
        ArrayList<String> userNames = userManager.getUsers();
        for (String name: userNames) {
            System.out.println(name);
        }
        System.out.println("new: make new user");
        System.out.println("none: exit user manager");
        return input.nextLine();
    }

    public void addUser(UserManager userManager) {
        System.out.println("What is your name");
        String name = input.nextLine();
        userManager.addUser(name);
    }

}
