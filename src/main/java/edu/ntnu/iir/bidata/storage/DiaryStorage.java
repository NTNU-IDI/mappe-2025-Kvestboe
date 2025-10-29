
package edu.ntnu.iir.bidata.storage;


import edu.ntnu.iir.bidata.model.DiaryEntry;
import edu.ntnu.iir.bidata.utils.Format;

import java.util.*;



public class DiaryStorage {
    // will handle storing data to json file
    // loading which diaries is related to user
    // handle sorting and filtering entries
    // will handle diary entries from model/Diary entry
    Format formatter = new Format();

    private static HashMap<Integer, DiaryEntry> entryMap = new HashMap<>();
    private int entryId = 0;

    // method for adding entry to storage
    public void addEntry(Scanner input) {
        System.out.print("Write in a title: ");
        String title = input.nextLine();
        System.out.print("Write in tags (space between different tags): ");
        ArrayList<String> tags = formatter.formatTags(input.nextLine());
        System.out.println("Write the content of the entry:");
        String content = input.nextLine();
        DiaryEntry entry = new DiaryEntry(title, tags, content);
        entryMap.put(entryId, entry);

        entry.editEntry(input);
        entryId++;
    }

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
            DiaryEntry entry = entryMap.get(key);
            System.out.println(key + ": "+entry.getTitle());

        }
    }

    static void searchTitle(Scanner input) {
        System.out.println("What title do you want to search by: ");
        String inputTitle = input.nextLine();
        for (DiaryEntry value: entryMap.values()) {
            if (value.getTitle().contains(inputTitle)) {
                System.out.println(value.getTitle());
            }
        }
    }

    static void searchTags(Scanner input) {
        System.out.println("What tag do you want to search by: ");
        String tag = input.nextLine();
        for (int key: entryMap.keySet()) {
            DiaryEntry entryValues = entryMap.get(key);
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

    }


    static void pickEntry(Scanner input) {
        int key;
        try {
            key = input.nextInt();
            if (entryMap.containsKey(key)) {
                DiaryEntry entry = entryMap.get(key);
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
