
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

    private HashMap<Integer, DiaryEntry> entryMap = new HashMap<>();
    private int entryId = 0;

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

}
