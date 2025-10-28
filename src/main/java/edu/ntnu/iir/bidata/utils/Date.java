package edu.ntnu.iir.bidata.utils;

import java.time.LocalDate;

public class Date {
    final private int day;
    final private int month;
    final private int year;

    public Date(int inputDay, int inputMonth, int inputYear) {
        day = inputDay;
        month = inputMonth;
        year = inputYear;
    }

    public Date() {
        LocalDate date = LocalDate.now();

        day = date.getDayOfMonth();
        month = date.getMonthValue();
        year = date.getYear();

    }

    public String getDate() {
        return day+"/"+month+"/"+year;
    }
}
