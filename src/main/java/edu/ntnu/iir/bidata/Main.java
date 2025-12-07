package edu.ntnu.iir.bidata;

import edu.ntnu.iir.bidata.ui.Controller;

/**
 * This class runs the program.
 */
class Main {
  public static void main(String[] args) {
    Controller controller = new Controller();
    controller.initialize();
    controller.start();
  }
}