package edu.ntnu.iir.bidata.model;


import java.time.LocalDate;
import java.util.ArrayList;

/**
 * The entry class represents an entry in the diary application.
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
     * @param author author of the entry
     * @param title title of entry
     * @param tags tags for the entry
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
        this.author = author;
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
        for (String tag: tags) {
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
        this.tags = tags;
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
     * @param day day of the entry
     * @param month month of the entry
     * @param year year of the entry
     */
    public void setDate(int day, int month, int year) {
        date = LocalDate.of(year, month, day);
    }


}
