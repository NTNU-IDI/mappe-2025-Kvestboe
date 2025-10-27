package edu.ntnu.iir.bidata.ui;

import edu.ntnu.iir.bidata.model.User;
import edu.ntnu.iir.bidata.storage.UserManager;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    public User createUser(Scanner input, UserManager userManager) {
        System.out.print("What is your name: ");
        User author = new User(input.nextLine());
        System.out.println("Hello, " +  author.getName() + "!");
        userManager.saveUser(author);
        return author;
    }

//    public User loadUser(String name) {
//        if (userManager.getUsers().contains(author.getName())) {
//            System.out.println("Brukeren eksisterer allerede...");
//        }
//    }

    public void userMenu(UserManager userManager) {
        ArrayList<String> userNames = userManager.getUsers();
        System.out.println("Change user:");
        for (String name: userNames) {
            System.out.println(name+".");
        }
        System.out.println("new: new user.");
    }

    public void showMenu() {
        System.out.println("Select your option:");
        System.out.println("new: new entry.");
        System.out.println("prior: view prior entries.");
        System.out.println("user: change user.");
        System.out.println("exit: exit.");
    }

    public void initEntry() {
        System.out.println("Write name of new entry: ");
    }

    public void priorEntries() {
        System.out.println("What do you want to sort the entries by: ");
    }
}
