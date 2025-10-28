package edu.ntnu.iir.bidata.utils;

import java.util.ArrayList;
import java.util.Collections;

public class Format {

    public ArrayList<String> formatTags(String inputTags) {
        ArrayList<String> tags = new ArrayList<>();
        String[] stringArray = inputTags.split("\\s+");

//        for (String test: stringArray) {
//            System.out.println(test);
//        }

        return tags;
    }
    // har en feil med formatering, returnerer ikke som forventet.
}
