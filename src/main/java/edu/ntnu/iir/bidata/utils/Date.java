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

    public int getYear() {
        return year;
    }
    public int getMonth() {
        return month;
    }
    public int getDay() {
        return day;
    }

    public String toString() {
        return day+"/"+month+"/"+year;
    }
}
