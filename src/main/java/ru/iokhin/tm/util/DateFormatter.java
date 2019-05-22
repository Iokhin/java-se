package ru.iokhin.tm.util;

import org.jetbrains.annotations.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {
    public static String format(@Nullable final Date date) {
        if (date == null) return null;
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm ");
        return formatter.format(date);
    }

    public static void main(String[] args) {
        Date date1 = new Date(0,0,3);
        Date date2 = new Date(0,0,3);
        System.out.println(date1.compareTo(date2));
        if (date1.after(date2)) System.out.println(format(date1));
        else System.out.println(format(date2));
    }
}


