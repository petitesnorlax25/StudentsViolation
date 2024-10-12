package com.studentsviolation.main.dto;
import java.util.ArrayList;
import java.util.List;

public class CategoryBOffenseDTO implements OffenseDTO {
    private int id;
    private String name;
    private String description;
    private String category; // Added category field
    private int penalty;

    public CategoryBOffenseDTO(int id, String name, String description, String category, int penalty) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category; // Initialize category
        this.penalty = penalty;
    }

    // Implement all methods from the interface
    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public int getPenalty() {
        return penalty;
    }

    @Override
    public String getCategory() {
        return category; // Implement getCategory() method
    }

    // Override toString method to utilize the fields
    @Override
    public String toString() {
        return "Offense ID: " + id + ", Name: " + name + ", Description: " + description + ", Category: " + category + ", Penalty: " + penalty;
    }

    public static List<CategoryBOffenseDTO> getCategoryBOffenses() {
        List<CategoryBOffenseDTO> offenses = new ArrayList<>();
        offenses.add(new CategoryBOffenseDTO(1, "Cheating", "Cheating during exams or assignments", "B", 1));
        offenses.add(new CategoryBOffenseDTO(2, "False Reporting", "Initiating false reports or complaints", "B", 1));
        offenses.add(new CategoryBOffenseDTO(3, "Disorderly Conduct", "Engaging in disorderly conduct on campus", "B", 1));
        offenses.add(new CategoryBOffenseDTO(4, "Damage to Property", "Maliciously damaging school property", "B", 1));
        offenses.add(new CategoryBOffenseDTO(5, "Alcohol Possession", "Possession of alcohol within school premises", "B", 1));
        offenses.add(new CategoryBOffenseDTO(6, "Plagiarism", "Engaging in plagiarism", "B", 2));
        offenses.add(new CategoryBOffenseDTO(7, "Assisting Cheating", "Helping another student cheat during exams", "B", 1));
        offenses.add(new CategoryBOffenseDTO(8, "Smoking", "Smoking in non-designated areas", "B", 1));
        offenses.add(new CategoryBOffenseDTO(9, "Unauthorized Selling", "Selling goods or services on school premises without permission", "B", 1));
        offenses.add(new CategoryBOffenseDTO(10, "Vandalism", "Defacing or vandalizing school property", "B", 2));
        offenses.add(new CategoryBOffenseDTO(11, "Gambling", "Engaging in gambling on school premises", "B", 2));
        offenses.add(new CategoryBOffenseDTO(12, "Insubordination", "Refusing to comply with school authorities", "B", 2));
        offenses.add(new CategoryBOffenseDTO(13, "Hate Speech", "Using hate speech against other students or staff", "B", 1));
        offenses.add(new CategoryBOffenseDTO(14, "Bullying", "Bullying or harassing fellow students", "B", 2));
        offenses.add(new CategoryBOffenseDTO(15, "Fighting", "Engaging in physical fights on school premises", "B", 2));
        offenses.add(new CategoryBOffenseDTO(16, "Public Intoxication", "Appearing intoxicated in public", "B", 2));
        offenses.add(new CategoryBOffenseDTO(17, "Disrespect to Staff", "Showing disrespect to staff members", "B", 1));
        offenses.add(new CategoryBOffenseDTO(18, "Trespassing", "Entering restricted areas without permission", "B", 1));
        offenses.add(new CategoryBOffenseDTO(19, "Harmful Pranks", "Playing harmful pranks on other students", "B", 1));
        offenses.add(new CategoryBOffenseDTO(20, "Disruptive Speech", "Making disruptive or offensive comments", "B", 1));
        offenses.add(new CategoryBOffenseDTO(21, "Obstruction of Justice", "Obstructing the course of school justice", "B", 2));
        offenses.add(new CategoryBOffenseDTO(22, "Refusal to Show ID", "Refusing to show identification when requested", "B", 1));
        offenses.add(new CategoryBOffenseDTO(23, "Unregistered Motor Vehicle", "Using an unregistered motor vehicle on school grounds", "B", 1));
        offenses.add(new CategoryBOffenseDTO(24, "False Identification", "Using false identification on school premises", "B", 1));
        offenses.add(new CategoryBOffenseDTO(25, "Misuse of Internet", "Misusing the school internet for illegal activities", "B", 1));
        offenses.add(new CategoryBOffenseDTO(26, "Violating Event Rules", "Violating rules at school-sponsored events", "B", 1));
        offenses.add(new CategoryBOffenseDTO(27, "Unauthorized Assembly", "Organizing unauthorized assemblies on campus", "B", 1));
        offenses.add(new CategoryBOffenseDTO(28, "Noise Pollution", "Causing excessive noise on campus", "B", 1));
        offenses.add(new CategoryBOffenseDTO(29, "Violation of Parking Rules", "Violating school parking rules", "B", 1));
        return offenses;
    }
}
