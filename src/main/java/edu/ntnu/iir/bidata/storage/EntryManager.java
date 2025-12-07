package edu.ntnu.iir.bidata.storage;

import edu.ntnu.iir.bidata.model.Author;
import edu.ntnu.iir.bidata.model.Entry;
import edu.ntnu.iir.bidata.model.Statistic;
import java.time.LocalDate;
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
    stat.incrementAuthorCount(entry.getAuthor());
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
    stat.decrementAuthorCount(entry.getAuthor());
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


// TODO: change the different searches to entrysort search in IoHandler.
// TODO: make IoHandler into multiple classes.
// TODO: make immutable copies for authormanager and entrymanager.
// TODO: edit diaries in a different way that supports this.

//  /**
//   * Getter for entries with title, if the entry contains(not equal) the title.
//   *
//   * @param title title of the tags
//   * @return all entries with the title
//   */
//  public HashMap<Integer, Entry> searchTitle(String title) {
//    HashMap<Integer, Entry> diaries = new HashMap<>();
//    for (int key : EntryMap.keySet()) {
//      Entry entry = EntryMap.get(key);
//      if (entry.getTitle().contains(title)) {
//        diaries.put(key, entry);
//      }
//
//    }
//    return diaries;
//  }

  /**
   * Getter for the entries with tag, if the entry tags contains(not equal) the tag.
   *
   * @param tag tag of the entry to search by
   * @return all diaries with the tag
   */
  public HashMap<Integer, Entry> searchTag(String tag) {
    HashMap<Integer, Entry> diaries = new HashMap<>();

    for (int key : EntryMap.keySet()) {
      Entry entry = EntryMap.get(key);
      if (entry.getTags().contains(tag)) {
        diaries.put(key, entry);
      }
    }
    return diaries;
  }

  /**
   * Getter for all entries with an author.
   *
   * @param author author of the entry
   * @return all diaries with the author
   */
  public HashMap<Integer, Entry> searchAuthor(Author author) {
    HashMap<Integer, Entry> entries = new HashMap<>();
    for (int key : EntryMap.keySet()) {
      Entry entry = EntryMap.get(key);
      Author entryAuthor = entry.getAuthor();
      if (author.getName().equals(entryAuthor.getName())) {
        entries.put(key, entry);
      }
    }
    return entries;
  }

  /**
   * Getter for all entries with specific date.
   *
   * @param date date of the entry/entries.
   * @return the entries of date
   */
  public HashMap<Integer, Entry> searchDate(LocalDate date) {
    HashMap<Integer, Entry> entries = new HashMap<>();
    for (int key : EntryMap.keySet()) {
      Entry entry = EntryMap.get(key);
      if (date.isEqual(entry.getDate())) {
        entries.put(key, entry);
      }
    }
    return entries;
  }

  /**
   * Getter for all entries within a certain time period.
   *
   * @param date1 date1 is the start date
   * @param date2 date2 is the end date
   * @return all entries within time period
   */
  public HashMap<Integer, Entry> searchPeriod(LocalDate date1, LocalDate date2) {
    HashMap<Integer, Entry> entries = new HashMap<>();
    for (int key : EntryMap.keySet()) {
      Entry entry = EntryMap.get(key);

      if (date1.isBefore(entry.getDate()) && date2.isAfter(entry.getDate())) {
        entries.put(key, entry);
      }
    }
    return entries;
  }

}
