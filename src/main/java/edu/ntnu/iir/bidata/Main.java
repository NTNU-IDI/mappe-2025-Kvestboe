package edu.ntnu.iir.bidata;

import edu.ntnu.iir.bidata.ui.Menu;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        Menu menu = new Menu();
        menu.initializeMenu(input);


        boolean runProgram = true;
        while (runProgram) {
            menu.showMenu();

            String menuChoice = input.nextLine();
            switch (menuChoice) {
                case "new" -> menu.newEntry();
                case "prior" -> menu.priorEntries();
                case "exit" -> runProgram = false;
            }

        }
    }

}