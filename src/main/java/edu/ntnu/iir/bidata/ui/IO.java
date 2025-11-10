package edu.ntnu.iir.bidata.ui;

import edu.ntnu.iir.bidata.model.User;
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
        System.out.print("Write in a title:");
        String title = input.nextLine();

        System.out.print("Write in a tags (with a space between): ");
        ArrayList<String> tags = formatTags(input.nextLine());

        System.out.println("Write in the content of the diary:");


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
    public User userSettings(UserManager userManager, User user) {
        boolean running = true;

        while (running) {
            String choice = userMenu(userManager, user);
            if (userManager.getUsers().contains(choice)) {
                return userManager.getUser(choice);
            } else if (choice.equals("new")) {
                return addUser(userManager);
            } else if (choice.equals("none")) {
                running = false;
            } else {
                System.out.println("Invalid choice...");
            }
        }

        return null;

    }

    private String userMenu(UserManager userManager, User user) {
        final String RESET = "\u001B[0m";
        final String GREEN = "\u001B[32m";
        ArrayList<String> userNames = userManager.getUsers();
        for (String name: userNames) {
            if (name.equals(user.getName())) {
                System.out.println(GREEN + name + RESET);
            } else {
                System.out.println(name);
            }
        }
        System.out.println("new: make new user");
        System.out.println("none: exit user manager");
        return input.nextLine();
    }

    public User addUser(UserManager userManager) {
        System.out.println("What is your name");
        String name = input.nextLine();
        userManager.addUser(name);
        return userManager.getUser(name);
    }

    // this section has some functions that the class methods rely on

    public ArrayList<String> formatTags(String inputTags) {
        ArrayList<String> tags = new ArrayList<>();
        String[] stringArray = inputTags.split("\\s");

        for (String tag: stringArray) {
            tags.add(tag);
        }
        return tags;
    }

}
