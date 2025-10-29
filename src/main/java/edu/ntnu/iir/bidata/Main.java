package edu.ntnu.iir.bidata;

import edu.ntnu.iir.bidata.model.User;
import edu.ntnu.iir.bidata.storage.DiaryStorage;
import edu.ntnu.iir.bidata.storage.UserManager;
import edu.ntnu.iir.bidata.ui.Menu;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        UserManager userManager = new UserManager();

        Menu menu = new Menu();
        User author = userManager.addUser(input);

        DiaryStorage entryStorage = new DiaryStorage();



        boolean runProgram = true;
        while (runProgram) {
            menu.showMenu();

            String menuChoice = input.nextLine();
            System.out.println();
            switch (menuChoice) {
                case "new" -> menu.addEntry(input, entryStorage);

                case "prior" -> menu.priorEntries(input, entryStorage);

                case "user" -> {
                    menu.userMenu(userManager);
                    String userChoice = input.nextLine();

                    System.out.println();

                    if (userManager.getUsers().contains(userChoice)) {
                        author = userManager.getUser(userChoice);
                        System.out.println("Hello, "+ author.getName());

                    } else if (userChoice.equals("new")) {
                        author = userManager.addUser(input);

                    } else {
                        System.out.println("Invalid choice.");

                    }
                }

                case "exit" -> runProgram = false;

            }

        }
    }

}