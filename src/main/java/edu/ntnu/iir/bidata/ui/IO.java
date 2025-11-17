package edu.ntnu.iir.bidata.ui;

import edu.ntnu.iir.bidata.model.Diary;
import edu.ntnu.iir.bidata.model.User;
import edu.ntnu.iir.bidata.storage.DiaryManager;
import edu.ntnu.iir.bidata.storage.UserManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class IO {
    /*
    this class is for handling input and output
    it handles the methods for the user choices
     */

    Scanner input = new Scanner(System.in);


    // this section will handle adding a new diary to the manager
    public void newDiary(DiaryManager diaryManager) {
        System.out.print("Write in a title: ");
        String title = input.nextLine();

        System.out.print("Write in a tags (with a space between): ");
        ArrayList<String> tags = formatTags(input.nextLine());

        System.out.println("Write in the content of the diary: ");
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
                case "title" -> editTitle(diary);
                case "tags" -> editTags(diary);
                case "date" -> editDate(diary);
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

    private void editTitle(Diary diary) {
        System.out.println("Do you wish to change the title to?");
        System.out.println("current title: " + diary.getTitle());
        System.out.println("none: to not change the title, and go back");
        System.out.print("changing title to: ");
        String newTitle = input.nextLine();
        if (!newTitle.equals("none")) {
            diary.setTitle(newTitle);
            System.out.println("changed title");
        } else {
            System.out.println("did not change the title");
        }

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

    private void editDate(Diary diary) {
        System.out.println("current date: " + diary.getDateString());
        System.out.println("write anything, not a number, to go back");
        try {
            System.out.print("day of year: ");
            int day = input.nextInt();
            System.out.print("month: ");
            input.nextLine();
            int month = input.nextInt();
            System.out.print("year: ");
            input.nextLine();
            int year = input.nextInt();
            input.nextLine();
            if ((0<day&&day<=31)&&(0<month&&month<=12)&&(0<year)) {
                diary.setDate(day, month, year);
            } else {
                System.out.println("not a valid date");
            }
        } catch (Exception e) {
            System.out.println("not a number");
            input.nextLine();
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
    public void priorDiaries(DiaryManager diaryManager) {

        boolean running = true;
        while (running) {
            String choice = priorDiariesMenu();
            switch (choice) {
                case "all" -> getAllDiaries(diaryManager);
                case "title" -> getDiariesTitle(diaryManager);
                case "tag" -> getDiariesTags(diaryManager);
                case "none" -> running = false;
            }
        }

    }


    private void getAllDiaries(DiaryManager diaryManager) {
        HashMap<Integer, Diary> diaries = diaryManager.allDiaries();

        for (int key: diaries.keySet()) {
            Diary diary = diaries.get(key);
            System.out.println(key + ": " + diary.getTitle());
        }

    }

    private void getDiariesTitle(DiaryManager diaryManager) {
        System.out.print("Write in the title you want to search by: ");
        String title = input.nextLine();
        HashMap<Integer, Diary> diaries = diaryManager.searchTitle(title);
        for (int key: diaries.keySet()) {
            Diary diary = diaries.get(key);
            System.out.println(key + ": " + diary.getTitle());
        }

    }

    private void getDiariesTags(DiaryManager diaryManager) {
        System.out.print("Write in the tag you want to search by: ");
        String tag = input.nextLine();
        HashMap<Integer, Diary> diaries = diaryManager.searchTag(tag);
        for (int key: diaries.keySet()) {
            Diary diary = diaries.get(key);
            System.out.println(key + ": " + diary.getTitle());
        }
    }

    private String priorDiariesMenu() {
        String choice = "";
        System.out.println("What do you wish to search the diaries by?");
        System.out.println("all: print all entries");
        System.out.println("title: search for a title");
        System.out.println("tag: search by tags");
        System.out.println("none: exit sorting menu");

        try {
            choice = input.nextLine();
        } catch (Exception e) {
            System.out.println("not a valid choice");
        }
        return choice;

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
