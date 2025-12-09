package edu.ntnu.iir.bidata.storage;

import edu.ntnu.iir.bidata.model.Entry;
import edu.ntnu.iir.bidata.model.Statistic;
import java.util.HashMap;

/**
 * The EntryManager class represents the diary in the application.
 *
 * <p>This is the storage for the entry objects, which stores the entries.</p>
 */
public class EntryManager {

  /**
   * The entries in the diary.
   */
  private final HashMap<Integer, Entry> entryMap = new HashMap<>();

  Statistic stat;

  /**
   * This is the constructor for the EntryManger class.
   *
   * <p>The statistic goes into EntryManager as it allows for easier updating of Statistic.</p>
   *
   * @param stat stat is an object of the Statistic class, and the stats to be recorded
   */
  public EntryManager(Statistic stat) {
    this.stat = stat;
  }

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
    entryMap.put(key, entry);
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
    for (int key : entryMap.keySet()) {
      if (entryMap.get(key) == entry) {
        entryMap.remove(key);
        break;
      }
    }
  }

  /**
   * This method acts as a setter and updates the statistics.
   * Although the statistic becomes an aggregation, it calls for easier management of stats.
   *
   * @param key key value of the entry
   * @param newEntry the new entry to replace the old one
   */
  public void updateEntry(int key, Entry newEntry) {
    Entry oldEntry = entryMap.get(key);
    stat.decrementAuthorCount(oldEntry.getAuthor().getName());
    stat.decrementEntriesThisMonth(oldEntry.getDate());
    oldEntry.getTags().forEach(tag -> stat.decrementTagCount(tag));
    entryMap.put(key, newEntry);
    stat.incrementAuthorCount(newEntry.getAuthor().getName());
    stat.incrementEntriesThisMonth(newEntry.getDate());
    newEntry.getTags().forEach(tag -> stat.incrementTagCount(tag));

  }

  /**
   * Getter for the statistics.
   *
   * @return statistic object
   */
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
    return entryMap.get(key);
  }

  /**
   * Getter for all the entries in the diary.
   *
   * @return all entries.
   */

  public HashMap<Integer, Entry> getDiary() {
    return new HashMap<>(entryMap);
  }

}
