package edu.ntnu.iir.bidata.utils;

import edu.ntnu.iir.bidata.model.Author;
import edu.ntnu.iir.bidata.model.Entry;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.function.Predicate;

/**
 * This class is responsible for the sorting of the diaries.
 *
 * <p>This class makes for less clutter in the EntryManager class,
 * as the EntryController calls to this class instead to call for sorting</p>
 */
public class EntrySort {

  /**
   * This method was inspired by geeks for geeks
   * <a href="https://www.geeksforgeeks.org/java/java-8-predicate-with-examples/">GeeksforGeeks</a>
   * and was explained how to use in my project with ai.
   *
   * @param diary the diary
   * @return a sorted list based on the condition
   */
  static HashMap<Integer, Entry> searchEntries(HashMap<Integer, Entry> diary,
      Predicate<Entry> condition) {
    HashMap<Integer, Entry> sortedEntries = new HashMap<>();
    for (int key : diary.keySet()) {
      Entry entry = diary.get(key);
      if (condition.test(entry)) {
        sortedEntries.put(key, entry);
      }
    }
    return sortedEntries;
  }

  /**
   * This method sorts based on if the title is contained.
   *
   * @param diary the diary
   * @param title title to search by
   * @return sorted list
   */
  public static HashMap<Integer, Entry> searchTitle(HashMap<Integer, Entry> diary, String title) {
    return searchEntries(diary, e -> e.getTitle().contains(title));
  }

  /**
   * This method sorts based on if the tag.
   *
   * @param diary the diary
   * @param tag tag to search by
   * @return sorted list
   */
  public static HashMap<Integer, Entry> searchTag(HashMap<Integer, Entry> diary, String tag) {
    return searchEntries(diary, e -> e.getTags().contains(tag));
  }

  /**
   * This method sorts based on the author.
   *
   * @param diary the diary
   * @param author author to search by
   * @return sorted list
   */
  public static HashMap<Integer, Entry> searchAuthor(HashMap<Integer, Entry> diary, Author author) {
    return searchEntries(diary, e -> e.getAuthor().getName().equals(author.getName()));
  }

  /**
   * This method will return all entries on a specific date.
   *
   * @param diary diary of the application
   * @param date date of the entries to be found
   * @return an HashMap of entries on specific date
   */
  public static HashMap<Integer, Entry> searchDate(HashMap<Integer, Entry> diary, LocalDate date) {
    return searchEntries(diary, e -> e.getDate().isEqual(date));
  }

  /**
   * This method sorts based on a time period.
   * The exclamation points in the lambda statements serves to include the date.
   *
   * @param diary the diary
   * @param start start date
   * @param end end date
   * @return sorted list
   */
  public static HashMap<Integer, Entry> searchPeriod(HashMap<Integer, Entry> diary,
      LocalDate start, LocalDate end) {
    return searchEntries(diary, e -> (!e.getDate().isAfter(start) && !e.getDate().isBefore(end)));
  }

}
