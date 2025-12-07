package edu.ntnu.iir.bidata.utils;

import edu.ntnu.iir.bidata.model.Author;
import edu.ntnu.iir.bidata.model.Entry;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.function.Predicate;


public class EntrySort {

  /**
   * This method was inspired by geeks for geeks
   * https://www.geeksforgeeks.org/java/java-8-predicate-with-examples/
   * and was explained how to use in my project with ai.
   *
   * @param diary
   * @return
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

  public HashMap<Integer, Entry> searchTitle(HashMap<Integer, Entry> diary, String title) {
    return searchEntries(diary, e -> e.getTitle().contains(title));
  }

  public HashMap<Integer, Entry> searchTag(HashMap<Integer, Entry> diary, String tag) {
    return searchEntries(diary, e -> e.getTags().contains(tag));
  }

  public HashMap<Integer, Entry> searchAuthor(HashMap<Integer, Entry> diary, Author author) {
    return searchEntries(diary, e -> e.getAuthor().getName().equals(author.getName()));
  }

  public HashMap<Integer, Entry> searchDate(HashMap<Integer, Entry> diary, LocalDate date) {
    return searchEntries(diary, e -> e.getDate().isEqual(date));
  }

  /**
   * This method serves to search for entries in between two dates.
   * The exclamation points in the lambda statements serves to include the date.
   *
   * @param diary
   * @param start
   * @param end
   * @return
   */
  public HashMap<Integer, Entry> searchPeriod(HashMap<Integer, Entry> diary,
      LocalDate start, LocalDate end) {
    return searchEntries(diary, e -> (!e.getDate().isAfter(start) && !e.getDate().isBefore(end)));
  }

}
