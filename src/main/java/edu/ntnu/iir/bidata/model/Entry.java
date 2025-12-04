package edu.ntnu.iir.bidata.model;


import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Represents a single diary entry object.
 */

public class Entry {
    // hold entry data, date, food, tags, content
    // communicates with ui and storage
    // part that gets displayed, edited, saved by other programs in the project

    private Author author;
    private String title;
    private ArrayList<String> tags;
    private String content;
    private LocalDate date;



    /**
     * @param title sets title of entry
     * @param tags sets list of tags for entry
     * @param content set the content for the entry
     */
    public Entry(Author author, String title, ArrayList<String> tags, String content) {
        this.author = author;
        this.title = title;
        this.tags = tags;
        this.content = content;
        date = LocalDate.now();

    }


    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String inputTitle) {
        title = inputTitle;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public String getTagsString() {
        StringBuilder tagString = new StringBuilder();
        for (String tag: tags) {
            tagString.append(tag).append(" ");
        }
        return tagString.toString();
    }

    public void setTags(ArrayList<String> inputTags) {
        tags = inputTags;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getDateString() {
        return date.toString();
    }

    public void setDate(int day, int month, int year) {
        date = LocalDate.of(year, month, day);
    }


}
