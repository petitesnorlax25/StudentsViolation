package com.studentsviolation.main.dto;
import java.util.ArrayList;
import java.util.List;

public class CategoryCOffenseDTO implements OffenseDTO {
    private int id;
    private String name;
    private String description;
    private String category; // Added category field
    private int penalty;

    public CategoryCOffenseDTO(int id, String name, String description, String category, int penalty) {
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

    public static List<CategoryCOffenseDTO> getCategoryCOffenses() {
        List<CategoryCOffenseDTO> offenses = new ArrayList<>();
        offenses.add(new CategoryCOffenseDTO(1, "Loitering", "Loitering in corridors and off-limits areas", "C", 1));
        offenses.add(new CategoryCOffenseDTO(2, "Dress Code Violation", "Violation of the dress code or uniform policy", "C", 1));
        offenses.add(new CategoryCOffenseDTO(3, "Disruptive Behavior", "Disruptive behavior during class or activities", "C", 1));
        offenses.add(new CategoryCOffenseDTO(4, "Unauthorized Eating", "Eating in classrooms without authorization", "C", 1));
        offenses.add(new CategoryCOffenseDTO(5, "Public Display of Affection", "Engaging in public displays of affection on campus", "C", 1));
        offenses.add(new CategoryCOffenseDTO(6, "Failure to Respond", "Failure to respond to administrative summons", "C", 1));
        offenses.add(new CategoryCOffenseDTO(7, "Littering", "Littering in school premises", "C", 1));
        offenses.add(new CategoryCOffenseDTO(8, "Late Submission", "Late submission of assignments or projects", "C", 1));
        offenses.add(new CategoryCOffenseDTO(9, "Tardiness", "Habitual tardiness in attending classes", "C", 1));
        offenses.add(new CategoryCOffenseDTO(10, "Sleeping in Class", "Sleeping during class or activities", "C", 1));
        offenses.add(new CategoryCOffenseDTO(11, "Unpreparedness", "Being unprepared for class", "C", 1));
        offenses.add(new CategoryCOffenseDTO(12, "No ID", "Failure to wear identification card on campus", "C", 1));
        offenses.add(new CategoryCOffenseDTO(13, "Disruptive Phone Use", "Using phone disruptively during class", "C", 1));
        offenses.add(new CategoryCOffenseDTO(14, "Unclean Uniform", "Wearing a dirty or unkempt uniform", "C", 1));
        offenses.add(new CategoryCOffenseDTO(15, "Unauthorized Photography", "Taking unauthorized photographs on school premises", "C", 1));
        offenses.add(new CategoryCOffenseDTO(16, "Noisy Behavior", "Engaging in noisy behavior in restricted areas", "C", 1));
        offenses.add(new CategoryCOffenseDTO(17, "Disorganized Workspace", "Leaving workspaces disorganized or messy", "C", 1));
        offenses.add(new CategoryCOffenseDTO(18, "Neglect of Duty", "Neglecting assigned duties or responsibilities", "C", 1));
        offenses.add(new CategoryCOffenseDTO(19, "Misbehavior During Drill", "Misbehavior during fire or emergency drills", "C", 1));
        offenses.add(new CategoryCOffenseDTO(20, "Unauthorized Absence", "Leaving class or premises without permission", "C", 1));
        return offenses;
    }
}
