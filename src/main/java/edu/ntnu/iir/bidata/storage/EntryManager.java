package edu.ntnu.iir.bidata.storage;

import edu.ntnu.iir.bidata.model.Entry;
import edu.ntnu.iir.bidata.model.Statistic;
import java.util.Collections;
import java.util.HashMap;

/**
 * The EntryManager class represents the diary in the application, where the entries are saved.
 */
public class EntryManager {
  // loading which diaries is related to user
  // handle sorting and filtering entries
  // will handle diary entries from model/Diary entry

  /**
   * The entries in the diary.
   */
  private final HashMap<Integer, Entry> EntryMap = new HashMap<>();

  private final Statistic stat = new Statistic();

  /**
   * The key of the entries.
   */
  private int key = 0;

  /**
   * Method for adding a new entry to the diary.
   *
   * @param entry entry object
   * @return the id of the entry added
   */
  public int addEntry(Entry entry) {
    EntryMap.put(key, entry);
    stat.incrementAuthorCount(entry.getAuthor().getName());
    for (String tag : entry.getTags()) {
      stat.incrementTagCount(tag);
    }
    stat.incrementEntriesThisMonth(entry.getDate());
    key++;
    return key - 1;
  }

  /**
   * Method for deleting an entry of the diary.
   *
   * @param entry the entry to be deleted
   */
  public void deleteEntry(Entry entry) {
    stat.decrementAuthorCount(entry.getAuthor().getName());
    for (String tag : entry.getTags()) {
      stat.decrementTagCount(tag);
    }
    stat.decrementEntriesThisMonth(entry.getDate());
    for (int key : EntryMap.keySet()) {
      if (EntryMap.get(key) == entry) {
        EntryMap.remove(key);
        break;
      }
    }
  }

  public void updateEntry(int key, Entry newEntry) {
    Entry oldEntry = EntryMap.get(key);
    stat.decrementAuthorCount(oldEntry.getAuthor().getName());
    stat.decrementEntriesThisMonth(oldEntry.getDate());
    oldEntry.getTags().forEach(tag -> stat.decrementTagCount(tag));
    EntryMap.put(key, newEntry);
    stat.incrementAuthorCount(newEntry.getAuthor().getName());
    stat.incrementEntriesThisMonth(newEntry.getDate());
    newEntry.getTags().forEach(tag -> stat.incrementTagCount(tag));

  }

  public Statistic getStatistic() {
    return stat;
  }

  /**
   * Getter for a specific entry in the diary.
   *
   * @param key the key of the diary
   * @return an entry
   */
  public Entry getEntry(int key) {
    return EntryMap.get(key);
  }

  /**
   * Getter for all the entries in the diary.
   *
   * @return all entries.
   */
  public HashMap<Integer, Entry> getEntries() {
    return EntryMap;
  }

  public HashMap<Integer, Entry> getDiary() {
    return (HashMap<Integer, Entry>) Collections.unmodifiableMap(EntryMap);
  }

// TODO: change the different searches to entrysort search in IoHandler.
// TODO: make IoHandler into multiple classes.
// TODO: make immutable copies for authormanager and entrymanager.
// TODO: edit diaries in a different way that supports this.


}
