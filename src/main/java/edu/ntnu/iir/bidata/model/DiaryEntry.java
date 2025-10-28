package edu.ntnu.iir.bidata.model;

import edu.ntnu.iir.bidata.utils.Date;
import edu.ntnu.iir.bidata.utils.Format;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class DiaryEntry {
    // hold entry data, date, food, tags, content
    // communicates with ui and storage
    // part that gets displayed, edited, saved by other programs in the project

    private String title;
    private static ArrayList<String> tags;
    final private String content;
    final private Date date;

    static Format formatter = new Format();

    // diary entry without custom date
    public DiaryEntry(String inputTitle, ArrayList<String> inputTags, String inputContent) {
        title = inputTitle;
        tags = inputTags;
        content = inputContent;
        date = new Date();

    }

    // diary entry with custom date
//    public DiaryEntry(String inputTitle, ArrayList<String> inputTags, String inputContent, Date inputDate) {
//        title = inputTitle;
//        tags = inputTags;
//        content = inputContent;
//        date = inputDate;
//
//    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String inputTitle) {
        title = inputTitle;
    }

    public ArrayList<String> getTags() {
        return tags;
    }
    public void setTags(ArrayList<String> inputTags) {
        tags = inputTags;
    }

    public void editEntry(Scanner input) {
        editMenu();
        String choice = input.nextLine();

        while (choice != "none") {
            switch (choice) {
                case "title" -> editTitle(input);
                case "tag" -> editTags(input);
                case "date" -> editDate(input);
                case "none" -> System.out.println();
            }
            editMenu();
            choice = input.nextLine();
        }

    }

    static void editMenu() {
        System.out.println("Is there anything you want to edit: ");
        System.out.println("title: edit the title");
        System.out.println("tag: edit the tags");
        System.out.println("date: edit the date");
        System.out.println("content: edit the content");
        System.out.println("none: add entry");
    }

    public void editTitle(Scanner input) {
        System.out.println("Previous title: " + title);
        System.out.print("What do you want to change the title to:");
        title = input.nextLine();
    }

    public void editTags(Scanner input) {
        System.out.println("Previous tags: " + tags);
        System.out.println("Have a space between different tags.");
        System.out.println("add: add tags");
        System.out.println("remove: remove tags");
        String choice = input.nextLine();
        switch (choice) {
            case "add" -> addTags(input.nextLine());
            case "remove" -> removeTags(input.nextLine());
        }
    }

    public void editDate(Scanner input) {
        System.out.println("Previous date: " + date);
        System.out.println("What do you want to change the date to: ");
        System.out.print("Day of month: ");
        int day = input.nextInt();
        System.out.print("Month: ");
        int month = input.nextInt();
        System.out.print("Year: ");
        int year = input.nextInt();
    }

    static void addTags(String inputTags) {
        ArrayList<String> newTags = formatter.formatTags(inputTags);

        tags.addAll(newTags);
    }

    static void removeTags(String inputTags) {
        ArrayList<String> newTags = formatter.formatTags(inputTags);

        tags.removeAll(newTags);
    }


}
