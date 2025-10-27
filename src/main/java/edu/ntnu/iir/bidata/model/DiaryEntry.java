package edu.ntnu.iir.bidata.model;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.time.LocalDate;

public class DiaryEntry {
    // hold entry data, date, food, tags, content
    // communicates with ui and storage
    // part that gets displayed, edited, saved by other programs in the project
    private final String title;
    private ArrayList<String> tags;
    private String content;
    private LocalDate date;

    private DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("E, MMM dd yyyy");

    public DiaryEntry(String inputTitle, ArrayList<String> inputTags, String inputContent) {
        title = inputTitle;
        tags = inputTags;
        content = inputContent;
        date = LocalDate.now();

    }

    public DiaryEntry(String inputTitle, ArrayList<String> inputTags, String inputContent, LocalDate inputDate) {
        title = inputTitle;
        tags = inputTags;
        content = inputContent;
        date = inputDate;

    }
}
