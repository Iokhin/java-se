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
}


