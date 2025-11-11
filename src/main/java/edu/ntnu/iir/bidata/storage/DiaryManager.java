
package edu.ntnu.iir.bidata.storage;


import edu.ntnu.iir.bidata.model.Diary;
import edu.ntnu.iir.bidata.utils.Format;

import java.util.*;



public class DiaryManager {
    // loading which diaries is related to user
    // handle sorting and filtering entries
    // will handle diary entries from model/Diary entry
    Format formatter = new Format();

    private static HashMap<Integer, Diary> entryMap = new HashMap<>();
    private int entryId = 0;

    public int addDiary(Diary diary) {
        entryMap.put(entryId, diary);
        entryId++;
        return entryId-1;
    }

    public Diary getDiary(int entryID) {
        return entryMap.get(entryID);
    }

    // old method
//    // method for adding entry to storage
//    public void addEntry(Scanner input) {
//        System.out.print("Write in a title: ");
//        String title = input.nextLine();
//        System.out.print("Write in tags (space between different tags): ");
//        ArrayList<String> tags = formatter.formatTags(input.nextLine());
//        System.out.println("Write the content of the entry:");
//        String content = input.nextLine();
//        Diary entry = new Diary(title, tags, content);
//        entryMap.put(entryId, entry);
//
//        entry.editEntry(input);
//        entryId++;
//    }

    public void priorEntries(Scanner input) {
        if (entryId != 0) {
            printSortMenu();

            String choice = input.nextLine();
            while (!choice.equals("none")) {
                switch (choice) {
                    case "all" -> allEntries();
                    case "title" -> searchTitle(input);
                    case "tag" -> searchTags(input);
                }
                pickEntry(input);
                printSortMenu();
                choice = input.nextLine();
            }
        } else {
            System.out.println("No entries found");
        }

    }


    static void allEntries() {
        for (int key: entryMap.keySet()) {
            Diary entry = entryMap.get(key);
            System.out.println(key + ": "+entry.getTitle());

        }
    }

    static void searchTitle(Scanner input) {
        System.out.println("What title do you want to search by: ");
        String inputTitle = input.nextLine();
        for (Diary value: entryMap.values()) {
            if (value.getTitle().contains(inputTitle)) {
                System.out.println(value.getTitle());
            }
        }
    }

    static void searchTags(Scanner input) {
        System.out.println("What tag do you want to search by: ");
        String tag = input.nextLine();
        for (int key: entryMap.keySet()) {
            Diary entryValues = entryMap.get(key);
            if (entryValues.getTags().contains(tag)) {
                System.out.println(key + ": " + entryValues.getTitle());
            }
        }
    }

    static void printSortMenu() {
        System.out.println("What do you want to sort the entries by:");
        System.out.println("all: print all entries");
        System.out.println("title: search for a title");
        System.out.println("tag: search by tags");
        System.out.println("none: exit sorting menu");

    }


    static void pickEntry(Scanner input) {
        int key;
        try {
            key = input.nextInt();
            if (entryMap.containsKey(key)) {
                Diary entry = entryMap.get(key);
                entry.getContent();
                entry.editEntry(input);
            } else {
                System.out.println("Not a valid entry...");
            }
        } catch (Exception e) {
            System.out.println("NaN");
        }

    }

}
