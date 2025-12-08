package edu.ntnu.iir.bidata.utils;

import edu.ntnu.iir.bidata.model.Author;
import edu.ntnu.iir.bidata.model.Entry;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.function.Predicate;


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
