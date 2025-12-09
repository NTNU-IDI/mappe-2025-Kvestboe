package edu.ntnu.iir.bidata.model;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * The entry class represents an entry in the diary application.
 *
 * <p>This is the data of the entry,
 * this is the class the diary is based on and is the information each entry.</p>
 */
public class Entry {

  /**
   * The author of the entry.
   */
  private Author author;

  /**
   * The title of the entry.
   */
  private String title;

  /**
   * The tags of the entry.
   */
  private ArrayList<String> tags;

  /**
   * The content of the entry.
   */
  private String content;

  /**
   * The date of the entry.
   */
  private LocalDate date;

  /**
   * Constructor of the entry.
   *
   * @param author  author of the entry
   * @param title   title of entry
   * @param tags    tags for the entry
   * @param content content of the entry
   */
  public Entry(Author author, String title, ArrayList<String> tags, String content) {
    this.author = author;
    this.title = title;
    this.tags = tags;
    this.content = content;
    date = LocalDate.now();

  }

  /**
   * Getter for the author of entry.
   *
   * @return author of the entry
   */
  public Author getAuthor() {
    return author;
  }

  /**
   * Setter method for the author of the entry.
   *
   * @param author author of entry
   */
  public void setAuthor(Author author) {
    if (this.author != null) {
      this.author = author;
    }
  }

  /**
   * Getter for the title of the entry.
   *
   * @return title of the entry
   */
  public String getTitle() {
    return title;
  }

  /**
   * Setter for the title of the entry.
   *
   * @param title title of the entry
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * Getter for the tags of the entry.
   *
   * @return tags of entry
   */
  public ArrayList<String> getTags() {
    return tags;
  }

  /**
   * Gets a string type of the tags, useful for printing tags.
   *
   * @return string of tags with space between
   */
  public String getTagsString() {
    StringBuilder tagString = new StringBuilder();
    for (String tag : tags) {
      tagString.append(tag).append(" ");
    }
    return tagString.toString();
  }

  /**
   * Setter for the tags of the entry.
   *
   * @param tags tags of entry
   */
  public void setTags(ArrayList<String> tags) {
    if (tags != null) {
      this.tags = tags;
    }
  }

  /**
   * Getter for the content of the entry.
   *
   * @return the content of the diary
   */
  public String getContent() {
    return content;
  }

  /**
   * Setter for the content of the entry.
   *
   * @param content content of the diary
   */
  public void setContent(String content) {
    this.content = content;
  }

  /**
   * Getter for the date of the entry.
   *
   * @return the date of te entry
   */
  public LocalDate getDate() {
    return date;
  }

  /**
   * Gets a string version of the date.
   *
   * @return string of date
   */
  public String getDateString() {
    return date.toString();
  }

  /**
   * Setter for the date of the entry.
   *
   * @param localDate date of the entry
   */
  public void setDate(LocalDate localDate) {
    if (localDate != null) {
      date = localDate;
    }
  }

  /**
   * Creates a copy of this entry.
   *
   * <p>This method is returns an immutable version af an entry, for better encapsulation.</p>
   *
   * @return a new Entry object with the same values
   */
  public Entry copy() {
    Entry copy = new Entry(this.author, this.title, new ArrayList<>(this.tags), this.content);
    copy.setDate(this.date);
    return copy;
  }

}
