package edu.ntnu.iir.bidata.ui;

import edu.ntnu.iir.bidata.model.DiaryEntry;
import edu.ntnu.iir.bidata.model.User;
import edu.ntnu.iir.bidata.storage.DiaryStorage;
import edu.ntnu.iir.bidata.storage.UserManager;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {


    // print user options
    public void userMenu(UserManager userManager) {
        ArrayList<String> userNames = userManager.getUsers();
        System.out.println("Change user:");
        for (String name: userNames) {
            System.out.println(name+".");
        }
        System.out.println("new: new user.");
    }

    // print menu options
    public void showMenu() {
        System.out.println("Select your option:");
        System.out.println("new: new entry.");
        System.out.println("prior: view prior entries.");
        System.out.println("user: change user.");
        System.out.println("exit: exit.");
    }

    // print entry options
    public void addEntry(Scanner input) {
        DiaryStorage newEntry = new DiaryStorage();
        newEntry.addEntry(input);
    }

    // option for selecting prior entries
    public void priorEntries() {
        System.out.println("What do you want to sort the entries by: ");
    }
}
