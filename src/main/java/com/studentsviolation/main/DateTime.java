package com.studentsviolation.main;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTime {
    public String getDateTime() {
        // Get the current date and time
        LocalDateTime now = LocalDateTime.now();
        
        // Define the desired format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy, HH:mm:ss");
        
        // Format the current date and time
        String formattedDateTime = now.format(formatter);
        
        // Print the formatted date and time
        System.out.println("Current date and time: " + formattedDateTime);
        return formattedDateTime;
    }
    public String getTimeDate() {
        // Get the current date and time
        LocalDateTime now = LocalDateTime.now();
        
        // Define the desired format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM, yyyy, HH:mm:ss");
        
        // Format the current date and time
        String formattedDateTime = now.format(formatter);
        
        // Print the formatted date and time
        System.out.println("Current date and time: " + formattedDateTime);
        return formattedDateTime;
    }
}
