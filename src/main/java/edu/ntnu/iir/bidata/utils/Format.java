package edu.ntnu.iir.bidata.utils;

import java.util.ArrayList;

public class Format {

    public ArrayList<String> formatTags(String inputTags) {
        ArrayList<String> tags = new ArrayList<>();
        String[] stringArray = inputTags.split("\\s");

        for (String tag: stringArray) {
            tags.add(tag);
        }
        return tags;
    }
    // har en feil med formatering, returnerer ikke som forventet.
    public static void main(String[] args) {
        Format test = new Format();
        System.out.println(test.formatTags("dette er en test"));
    }
}
