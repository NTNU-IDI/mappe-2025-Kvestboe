package edu.ntnu.iir.bidata.ui;

import edu.ntnu.iir.bidata.model.Diary;
import edu.ntnu.iir.bidata.model.User;
import edu.ntnu.iir.bidata.storage.DiaryManager;
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
    public void newDiary(DiaryManager diaryManager) {
        System.out.print("Write in a title:");
        String title = input.nextLine();

        System.out.print("Write in a tags (with a space between): ");
        ArrayList<String> tags = formatTags(input.nextLine());

        System.out.println("Write in the content of the diary:");
        String content = inputContent();

        Diary diary = new Diary(title, tags, content);
        int entry = diaryManager.addDiary(diary);

        editDiary(diaryManager.getDiary(entry));

    }

    // this section will handle the editing behind the diaries
    // most of those functions are in the diary class, which is bad practice
    public void editDiary(Diary diary) {
        boolean running = true;

        while (running) {
            String choice = editMenu(diary);
            switch (choice) {
                case "title" -> diary.setTitle(input.nextLine());
                case "tags" -> editTags(diary);
                case "none" -> running = false;
            }
        }

    }

    private String editMenu(Diary diary) {
        System.out.println("Is there anything you wish to edit?");
        System.out.println("title: " + diary.getTitle());
        System.out.println("tags: " + diary.getTagsString());
        System.out.println("date: " + diary.getDateString());
        System.out.println("content: view the content or edit it");
        System.out.println("none: go back");

        return input.nextLine();

    }

    private void editTags(Diary diary) {
        System.out.println("What do you wish to do with the tags:");
        System.out.println("add: add new tags");
        System.out.println("remove: remove tags");
        System.out.println("write anything to go back");

        String choice = input.nextLine();
        switch (choice) {
            case "add" -> addTags(diary);
            case "remove" -> removeTags(diary);
            default -> System.out.println("No option matches, " +choice+ ", going back");
        }

    }

    private void addTags(Diary diary) {
        System.out.println("Write the tags you wish to add (space between):");
        ArrayList<String> newTags = formatTags(input.nextLine());
        ArrayList<String> tags = diary.getTags();
        for (String tag: newTags) {
            if (tags.contains(tag)) {
                System.out.println("Tag already exists, " + tag);
            } else {
                tags.add(tag);
            }
        }
        diary.setTags(tags);
    }

    private void removeTags(Diary diary) {
        System.out.println("Write the tags you wish to remove (space between):");
        ArrayList<String> newTags = formatTags(input.nextLine());
        ArrayList<String> tags = diary.getTags();
        for (String tag: newTags) {
            if (tags.contains(tag)) {
                tags.remove(tag);
            } else {
                System.out.println("No tags match the tag, " + tag);
            }

        }
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

    private ArrayList<String> formatTags(String inputTags) {
        ArrayList<String> tags = new ArrayList<>();
        String[] stringArray = inputTags.split("\\s");

        for (String tag: stringArray) {
            if (!tags.contains(tag)) {
                tags.add(tag);
            }
        }
        return tags;
    }

    private String inputContent() {
        String content = "";
        boolean done = false;

        System.out.println("Write done, to stop content loop.");

        while (!done) {
            String line = input.nextLine();
            if (line.equals("done")) {
                done = true;

            } else {
                content += line + "\n";

            }
        }
        return content;
    }

}
