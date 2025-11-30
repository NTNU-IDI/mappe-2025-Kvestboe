
package edu.ntnu.iir.bidata.storage;


import edu.ntnu.iir.bidata.model.Entry;

import java.util.*;



public class EntryManager {
    // loading which diaries is related to user
    // handle sorting and filtering entries
    // will handle diary entries from model/Diary entry

    private final HashMap<Integer, Entry> entryMap = new HashMap<>();
    private int entryId = 0;

    public int addDiary(Entry entry) {
        entryMap.put(entryId, entry);
        entryId++;
        return entryId-1;
    }

    public Entry getDiary(int entryID) {
        return entryMap.get(entryID);
    }

    public HashMap<Integer, Entry> allDiaries() {
        return entryMap;
    }

    public HashMap<Integer, Entry> searchTitle(String title) {
        HashMap<Integer, Entry> diaries = new HashMap<>();
        for (int key: entryMap.keySet()) {
            Entry entry = entryMap.get(key);
            if (entry.getTitle().contains(title)) {
                diaries.put(key, entry);
            }

        }
        return diaries;
    }

    public HashMap<Integer, Entry> searchTag(String tag) {
        HashMap<Integer, Entry> diaries = new HashMap<>();

        for (int key: entryMap.keySet()) {
            Entry entry = entryMap.get(key);
            if (entry.getTags().contains(tag)) {
                diaries.put(key, entry);
            }
        }
        return diaries;
    }


}
