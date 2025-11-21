package edu.ntnu.iir.bidata.model;

import edu.ntnu.iir.bidata.utils.Date;
import edu.ntnu.iir.bidata.utils.Format;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a single diary entry object.
 */

public class Diary {
    // hold entry data, date, food, tags, content
    // communicates with ui and storage
    // part that gets displayed, edited, saved by other programs in the project

    private String title;
    private ArrayList<String> tags;
    final private String content;
    private Date date;


    /**
     * @param title sets title of entry
     * @param tags sets list of tags for entry
     * @param content set the content for the entry
     */
    public Diary(String title, ArrayList<String> tags, String content) {
        this.title = title;
        this.tags = tags;
        this.content = content;
        date = new Date();

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
        String tagString = "";
        for (String tag: tags) {
            tagString += tag + " ";
        }
        return tagString;
    }

    public void setTags(ArrayList<String> inputTags) {
        tags = inputTags;
    }

    public String getContent() {
        return content;
    }

    public String getDateString() {
        return date.toString();
    }

    public void setDate(int day, int month, int year) {
        date = new Date(day, month, year);
    }


}
