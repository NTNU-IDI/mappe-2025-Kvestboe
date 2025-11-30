package edu.ntnu.iir.bidata.ui;

import edu.ntnu.iir.bidata.model.Author;
import edu.ntnu.iir.bidata.model.Entry;
import edu.ntnu.iir.bidata.storage.AuthorManager;
import edu.ntnu.iir.bidata.storage.EntryManager;

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
    public void newDiary(EntryManager entryManager, Author author, AuthorManager authorManager) {
        System.out.print("Write in a title: ");
        String title = input.nextLine();

        System.out.print("Write in a tags (with a space between): ");
        ArrayList<String> tags = formatTags(input.nextLine());

        System.out.println("Write in the content of the diary: ");
        String content = inputContent();

        Entry diary = new Entry(author, title, tags, content);
        int entry = entryManager.addDiary(diary);

        editDiary(entryManager.getDiary(entry), authorManager);

    }

    // this section will handle the editing behind the diaries
    // most of those functions are in the diary class, which is bad practice
    public void editDiary(Entry entry, AuthorManager authorManager) {
        boolean running = true;

        while (running) {
            String choice = editMenu(entry);
            switch (choice) {
                case "author" -> editUser(entry, authorManager);
                case "title" -> editTitle(entry);
                case "tags" -> editTags(entry);
                case "date" -> editDate(entry);
                case "content" -> editContent(entry);
                case "none" -> running = false;
            }
        }

    }

    private String editMenu(Entry entry) {
        System.out.println("Is there anything you wish to edit?");
        System.out.println("author: " + entry.getUser().getName());
        System.out.println("title: " + entry.getTitle());
        System.out.println("tags: " + entry.getTagsString());
        System.out.println("date: " + entry.getDateString());
        System.out.println("content: view the content or edit it");
        System.out.println("none: go back");

        return input.nextLine();

    }

    private void editUser(Entry entry, AuthorManager authorManager) {
        Author author = userSettings(authorManager, entry.getUser());
        if (author != null) {
            entry.setUser(author);
            System.out.println("Changed the author");
        } else {
            System.out.println("Did not change author.");
        }

    }

    private void editTitle(Entry entry) {
        System.out.println("Do you wish to change the title to?");
        System.out.println("current title: " + entry.getTitle());
        System.out.println("none: to not change the title, and go back");
        System.out.print("changing title to: ");
        String newTitle = input.nextLine();
        if (!newTitle.equals("none")) {
            entry.setTitle(newTitle);
            System.out.println("changed title");
        } else {
            System.out.println("did not change the title");
        }

    }

    private void editTags(Entry entry) {
        System.out.println("What do you wish to do with the tags:");
        System.out.println("add: add new tags");
        System.out.println("remove: remove tags");
        System.out.println("write anything to go back");

        String choice = input.nextLine();
        switch (choice) {
            case "add" -> addTags(entry);
            case "remove" -> removeTags(entry);
            default -> System.out.println("No option matches, " +choice+ ", going back");
        }

    }

    private void editDate(Entry entry) {
        System.out.println("current date: " + entry.getDateString());
        System.out.println("write anything, not a number, to go back");
        try {
            System.out.print("day of month: ");
            int day = input.nextInt();
            input.nextLine();
            System.out.print("month: ");
            input.nextLine();
            int month = input.nextInt();
            System.out.print("year: ");
            input.nextLine();
            int year = input.nextInt();
            input.nextLine();
            if ((0<day&&day<=31)&&(0<month&&month<=12)&&(0<year)) {
                entry.setDate(day, month, year);
            } else {
                System.out.println("not a valid date");
            }
        } catch (Exception e) {
            System.out.println("not a number");
            input.nextLine();
        }

    }

    private void editContent(Entry entry) {
        boolean running = true;
        while (running) {
            String choice = contentMenu();
            switch (choice) {
                case "read" -> System.out.println(entry.getContent());
                case "write" -> writeContent(entry);
                case "add" -> addContent(entry);
                case "none" -> running = false;


            }
        }

    }

    private String contentMenu() {
        System.out.println("What do you wish to do:");
        System.out.println("read: read the content of the diary");
        System.out.println("write: rewrite the content of the diary");
        System.out.println("add: add to the already existing content");
        System.out.println("none: exit the content menu");

        return input.nextLine();
    }

    private void writeContent(Entry entry) {
        String content = inputContent();
        entry.setContent(content);

    }

    private void addContent(Entry entry) {
        String content = entry.getContent();
        content += inputContent();
        entry.setContent(content);
    }

    private void addTags(Entry entry) {
        System.out.println("Write the tags you wish to add (space between):");
        ArrayList<String> newTags = formatTags(input.nextLine());
        ArrayList<String> tags = entry.getTags();
        for (String tag: newTags) {
            if (tags.contains(tag)) {
                System.out.println("Tag already exists, " + tag);
            } else {
                tags.add(tag);
            }
        }
        entry.setTags(tags);
    }

    private void removeTags(Entry entry) {
        System.out.println("Write the tags you wish to remove (space between):");
        ArrayList<String> newTags = formatTags(input.nextLine());
        ArrayList<String> tags = entry.getTags();
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
    public void priorDiaries(EntryManager entryManager, AuthorManager authorManager) {

        boolean running = true;
        boolean valid = true;
        while (running) {
            String choice = priorDiariesMenu();
            switch (choice) {
                case "all" -> getAllDiaries(entryManager);
                case "title" -> getDiariesTitle(entryManager);
                case "tag" -> getDiariesTags(entryManager);
                case "none" -> running = false;
                default -> valid = false;
            }
            if (running && valid) {
                Entry entry = pickDiary(entryManager);
                if (entry != null) {
                    editDiary(entry, authorManager);
                } else {
                    System.out.println("no existing diary was chosen");
                }
            }
        }

    }

    private Entry pickDiary(EntryManager entryManager) {
        System.out.print("Write in the number of the diary you want to pick:");
        try{
            int key = input.nextInt();
            input.nextLine();
            return entryManager.getDiary(key);
        } catch (Exception e) {
            System.out.println("not an option");
            return null;
        }
    }


    private void getAllDiaries(EntryManager entryManager) {
        HashMap<Integer, Entry> diaries = entryManager.allDiaries();

        for (int key: diaries.keySet()) {
            Entry entry = diaries.get(key);
            System.out.println(key + ": " + entry.getTitle());
        }

    }

    private void getDiariesTitle(EntryManager entryManager) {
        System.out.print("Write in the title you want to search by: ");
        String title = input.nextLine();
        HashMap<Integer, Entry> diaries = entryManager.searchTitle(title);
        for (int key: diaries.keySet()) {
            Entry entry = diaries.get(key);
            System.out.println(key + ": " + entry.getTitle());
        }

    }

    private void getDiariesTags(EntryManager entryManager) {
        System.out.print("Write in the tag you want to search by: ");
        String tag = input.nextLine();
        HashMap<Integer, Entry> diaries = entryManager.searchTag(tag);
        for (int key: diaries.keySet()) {
            Entry entry = diaries.get(key);
            System.out.println(key + ": " + entry.getTitle());
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
    public Author userSettings(AuthorManager authorManager, Author author) {
        boolean running = true;

        while (running) {
            String choice = userMenu(authorManager, author);
            if (authorManager.getUsers().contains(choice)) {
                return authorManager.getUser(choice);
            } else if (choice.equals("new")) {
                return addUser(authorManager);
            } else if (choice.equals("none")) {
                running = false;
            } else {
                System.out.println("Invalid choice...");
            }
        }

        return null;

    }

    private String userMenu(AuthorManager authorManager, Author author) {
        final String RESET = "\u001B[0m";
        final String GREEN = "\u001B[32m";
        ArrayList<String> userNames = authorManager.getUsers();
        for (String name: userNames) {
            if (name.equals(author.getName())) {
                System.out.println(GREEN + name + RESET);
            } else {
                System.out.println(name);
            }
        }
        System.out.println("new: make new user");
        System.out.println("none: exit user manager");
        return input.nextLine();
    }

    public Author addUser(AuthorManager authorManager) {
        System.out.println("What is your name");
        String name = input.nextLine();
        authorManager.addUser(name);
        return authorManager.getUser(name);
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
        StringBuilder content = new StringBuilder();
        boolean done = false;

        System.out.println("Write done, to stop content loop.");

        while (!done) {
            String line = input.nextLine();
            if (line.equals("done")) {
                done = true;

            } else {
                content.append(line).append("\n");

            }
        }
        return content.toString();
    }

}
