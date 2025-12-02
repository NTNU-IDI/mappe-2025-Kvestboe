
package edu.ntnu.iir.bidata.storage;


import edu.ntnu.iir.bidata.model.Entry;

import java.util.HashMap;
import edu.ntnu.iir.bidata.utils.Date;



public class EntryManager {
    // loading which diaries is related to user
    // handle sorting and filtering entries
    // will handle diary entries from model/Diary entry

    private final HashMap<Integer, Entry> entryMap = new HashMap<>();
    private int entryId = 0;

    public int addEntry(Entry entry) {
        entryMap.put(entryId, entry);
        entryId++;
        return entryId-1;
    }

    public void deleteEntry(Entry entry) {
       for (int key: entryMap.keySet()) {
           if (entryMap.get(key) == entry) {
               entryMap.remove(key);
           }
       }
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

    public HashMap<Integer, Entry> searchAuthor(String author) {
        HashMap<Integer, Entry> entries = new HashMap<>();
        for (int key: entryMap.keySet()) {
            Entry entry = entryMap.get(key);
            String name = entry.getAuthor().getName();
            if (author.equals(name)) {
               entries.put(key, entry);
            }
        }
        return entries;
    }

    public HashMap<Integer, Entry> searchDate(Date date) {
        HashMap<Integer, Entry> entries = new HashMap<>();
        for (int key: entryMap.keySet()) {
            Entry entry = entryMap.get(key);
            if (entry.getDateString().equals(date.toString())) {
                entries.put(key, entry);
            }
        }
        return entries;
    }

    public HashMap<Integer, Entry> searchPeriod(Date date1, Date date2) {
        HashMap<Integer, Entry> entries = new HashMap<>();
        for (int key: entryMap.keySet()) {
            Entry entry = entryMap.get(key);
            if (
                    (entry.getDate().getYear() > date1.getYear() && entry.getDate().getYear() < date2.getYear()) &&
                    (entry.getDate().getMonth() > date1.getMonth() && entry.getDate().getMonth() < date2.getMonth()) &&
                    (entry.getDate().getDay() > date1.getDay() && entry.getDate().getDay() < date2.getDay())
            ) {

                entries.put(key, entry);
            }
        }
        return entries;
    }


}
