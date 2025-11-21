
package edu.ntnu.iir.bidata.storage;


import edu.ntnu.iir.bidata.model.Diary;

import java.util.*;



public class DiaryManager {
    // loading which diaries is related to user
    // handle sorting and filtering entries
    // will handle diary entries from model/Diary entry

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


}
