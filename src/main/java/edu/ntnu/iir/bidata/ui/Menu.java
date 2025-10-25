package edu.ntnu.iir.bidata.ui;

import edu.ntnu.iir.bidata.model.User;

import java.util.Scanner;

public class Menu {

    public void initializeMenu(Scanner input) {
        System.out.print("What is your name: ");
        User author = new User(input.nextLine());
        System.out.println("Hello, " +  author.getName() + "!");
    }

    public void showMenu() {
        System.out.println("Select your option:");
        System.out.println("new: new entry.");
        System.out.println("prior: view prior entries.");
        System.out.println("exit: exit.");
    }

    public void newEntry() {
        System.out.println("Write name of new entry: ");
    }

    public void priorEntries() {
        System.out.println("What do you want to sort the entries by: ");
    }
}
