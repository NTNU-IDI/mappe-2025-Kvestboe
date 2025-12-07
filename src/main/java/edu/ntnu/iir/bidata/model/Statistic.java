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
  private final HashMap<String, Integer> authorCount = new HashMap<>();

  /**
   * Most occuring tags.
   */
  private final HashMap<String, Integer> tagCount =  new HashMap<>();

  /**
   * Number of entries this month.
   */
  private int entriesThisMonth = 0;

  /**
   * This method will increment the entry count,
   * and register the author if it was not in it from before.
   *
   * @param
   */
  public void incrementAuthorCount(String authorName) {
    authorCount.put(authorName, authorCount.getOrDefault(authorName, 0) + 1);
  }

  /**
   * This method will increment the tag count.
   *
   * @param tag tag is the to be incremented
   */
  public void incrementTagCount(String tag) {
    tagCount.put(tag, tagCount.getOrDefault(tag, 0) + 1);
  }

  /**
   * This method will increment the entries this month.
   *
   * @param date date is the date of the entry
   */
  public void incrementEntriesThisMonth(LocalDate date) {
    if (date.getMonth().equals(LocalDate.now().getMonth())
        && date.getYear() == LocalDate.now().getYear()) {
      entriesThisMonth++;
    }
  }

  /**
   * This method will decrement the entry count for author.
   *
   * @param
   */
  public void decrementAuthorCount(String authorName) {
    authorCount.put(authorName, authorCount.getOrDefault(authorName, 0) - 1);
  }

  /**
   * This method wil decrement the tag count.
   *
   * @param tag tag to be decremented
   */
  public void decrementTagCount(String tag) {
    tagCount.put(tag, tagCount.getOrDefault(tag, 0) - 1);
  }

  /**
   * This method will decrement the entries this month tag.
   */
  public void decrementEntriesThisMonth(LocalDate date) {
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
  public HashMap<String, Integer> getAuthorCount() {
    return authorCount;
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
