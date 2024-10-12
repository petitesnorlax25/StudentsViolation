package com.studentsviolation.main.dto;

public interface OffenseDTO {
    int getId();
    String getName();
    String getDescription();
    int getPenalty();
    String getCategory(); // Add this method to handle categories
}
