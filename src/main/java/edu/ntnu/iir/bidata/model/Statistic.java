package edu.ntnu.iir.bidata.model;

import java.time.LocalDate;
import java.util.HashMap;

/**
 * This class represent the statistics in the diary.
 */
public class Statistic {

  /**
   * This attribute has the number of entries per author.
   */
  HashMap<Author, Integer> entryCount = new HashMap<>();

  /**
   * Most occuring tags.
   */
  HashMap<String, Integer> tagCount =  new HashMap<>();

  /**
   * Number of entries this month.
   */
  int entriesThisMonth = 0;

  /**
   * This method will increment the entry count,
   * and register the author if it was not in it from before.
   *
   * @param author author of the diary
   */
  public void addEntryCount(Author author) {
    entryCount.put(author, entryCount.getOrDefault(author, 0) + 1);
  }

  /**
   * This method will increment the tag count.
   *
   * @param tag tag is the to be incremented
   */
  public void addTagCount(String tag) {
    tagCount.put(tag, tagCount.getOrDefault(tag, 0) + 1);
  }

  /**
   * This method will increment the entries this month.
   *
   * @param date date is the date of the entry
   */
  public void addEntriesThisMonth(LocalDate date) {
    if (date.getMonth().equals(LocalDate.now().getMonth())
        && date.getYear() == LocalDate.now().getYear()) {
      entriesThisMonth++;
    }
  }

  /**
   * This method will decrement the entry count for author.
   *
   * @param author author to decrement
   */
  public void removeEntryCount(Author author) {
    entryCount.put(author, entryCount.getOrDefault(author, 0) - 1);
  }

  /**
   * This method wil decrement the tag count.
   *
   * @param tag tag to be decremented
   */
  public void removeTagCount(String tag) {
    tagCount.put(tag, tagCount.getOrDefault(tag, 0) - 1);
  }

  /**
   * This method will decrement the entries this month tag.
   */
  public void removeEntriesThisMonth(LocalDate date) {
    if (date.getMonth().equals(LocalDate.now().getMonth())
        && date.getYear() == LocalDate.now().getYear()) {
      entriesThisMonth--;
    }
  }

  /**
   * Getter for the entry count.
   *
   * @return the entry count
   */
  public HashMap<Author, Integer> getEntryCount() {
    return entryCount;
  }

  /**
   * Getter for the tag count.
   *
   * @return the tag count
   */
  public HashMap<String, Integer> getTagCount() {
    return tagCount;
  }

  /**
   * Getter for the entries this month.
   *
   * @return the month entry count
   */
  public int getEntriesThisMonth() {
    return entriesThisMonth;
  }
}
