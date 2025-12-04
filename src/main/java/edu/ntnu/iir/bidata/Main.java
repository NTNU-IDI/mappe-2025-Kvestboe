package edu.ntnu.iir.bidata;

import edu.ntnu.iir.bidata.ui.Menu;

/**
 * This class runs the program.
 */
class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.initialize();
        menu.start();
    }

}