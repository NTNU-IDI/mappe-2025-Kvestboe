
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


//    public void priorEntries(Scanner input) {
//        if (entryId != 0) {
//            printSortMenu();
//
//            String choice = input.nextLine();
//            while (!choice.equals("none")) {
//                switch (choice) {
//                    case "all" -> allEntries();
//                    case "title" -> searchTitle(input);
//                    case "tag" -> searchTags(input);
//                }
//                pickEntry(input);
//                printSortMenu();
//                choice = input.nextLine();
//            }
//        } else {
//            System.out.println("No entries found");
//        }
//
//    }


    public HashMap<Integer, Diary> allDiaries() {
        return entryMap;
    }

    public HashMap<Integer, Diary> searchTitle(String title) {
        HashMap<Integer, Diary> diaries = new HashMap<>();
        for (int key: entryMap.keySet()) {
            Diary diary = entryMap.get(key);
            if (diary.getTitle().contains(title)) {
                diaries.put(key, diary);
            }

        }
        return diaries;
    }

    public HashMap<Integer, Diary> searchTag(String tag) {
        HashMap<Integer, Diary> diaries = new HashMap<>();

        for (int key: entryMap.keySet()) {
            Diary diary = entryMap.get(key);
            if (diary.getTags().contains(tag)) {
                diaries.put(key, diary);
            }
        }
        return diaries;
    }

//    static void printSortMenu() {
//        System.out.println("What do you want to sort the entries by:");
//        System.out.println("all: print all entries");
//        System.out.println("title: search for a title");
//        System.out.println("tag: search by tags");
//        System.out.println("none: exit sorting menu");
//
//    }


//    static void pickEntry(Scanner input) {
//        int key;
//        try {
//            key = input.nextInt();
//            if (entryMap.containsKey(key)) {
//                Diary entry = entryMap.get(key);
//                entry.getContent();
//                entry.editEntry(input);
//            } else {
//                System.out.println("Not a valid entry...");
//            }
//        } catch (Exception e) {
//            System.out.println("NaN");
//        }
//
//    }

}
