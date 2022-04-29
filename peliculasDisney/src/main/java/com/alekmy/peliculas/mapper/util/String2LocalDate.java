
package com.alekmy.peliculas.mapper.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class String2LocalDate {
    
    public static LocalDate string2LocalDate(String stringDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(stringDate, formatter);
        return date;
    }
}
