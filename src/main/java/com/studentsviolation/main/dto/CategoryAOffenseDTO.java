package com.studentsviolation.main.dto;
import java.util.ArrayList;
import java.util.List;

public class CategoryAOffenseDTO implements OffenseDTO {
    private int id;
    private String name;
    private String description;
    private String category; // Added category field
    private int penalty;

    public CategoryAOffenseDTO(int id, String name, String description, String category, int penalty) {
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

    public static List<CategoryAOffenseDTO> getCategoryAOffenses() {
        List<CategoryAOffenseDTO> offenses = new ArrayList<>();
        offenses.add(new CategoryAOffenseDTO(1, "Criminal Offense", "Conviction in court of a criminal offense", "A", 3));
        offenses.add(new CategoryAOffenseDTO(2, "Grave Misconduct", "Grave misconduct, gross immorality, or scandalous acts", "A", 1));
        offenses.add(new CategoryAOffenseDTO(3, "Drug-Related Offense", "Possession, distribution, or use of prohibited drugs", "A", 1));
        offenses.add(new CategoryAOffenseDTO(4, "Theft", "Engaging in theft activities", "A", 1));
        offenses.add(new CategoryAOffenseDTO(5, "Forgery", "Forging or falsifying academic records", "A", 1));
        offenses.add(new CategoryAOffenseDTO(6, "Instigation of Unlawful Activities", "Instigating unlawful activities", "A", 1));
        offenses.add(new CategoryAOffenseDTO(7, "Scandalous Acts", "Engaging in scandalous sexual acts", "A", 1));
        offenses.add(new CategoryAOffenseDTO(8, "Prostitution", "Engaging in prostitution", "A", 1));
        offenses.add(new CategoryAOffenseDTO(9, "Physical Injury", "Inflicting physical injury on others", "A", 1));
        offenses.add(new CategoryAOffenseDTO(10, "Hazing", "Involvement in hazing or violent activities", "A", 3));
        offenses.add(new CategoryAOffenseDTO(11, "Possession of Dangerous Weapons", "Unauthorized possession of dangerous weapons", "A", 3));
        offenses.add(new CategoryAOffenseDTO(12, "Unlawful Arrest", "Unlawfully arresting or detaining someone", "A", 2));
        offenses.add(new CategoryAOffenseDTO(13, "Extortion", "Extorting money or favors from students or staff", "A", 3));
        offenses.add(new CategoryAOffenseDTO(14, "Blackmail", "Engaging in blackmail or coercion", "A", 3));
        offenses.add(new CategoryAOffenseDTO(15, "Bribery", "Offering or accepting bribes for academic favors", "A", 3));
        offenses.add(new CategoryAOffenseDTO(16, "Slander", "Slanderous or libelous accusations", "A", 2));
        offenses.add(new CategoryAOffenseDTO(17, "Unauthorized Entry", "Breaking into restricted areas", "A", 2));
        offenses.add(new CategoryAOffenseDTO(18, "Arson", "Engaging in arson or starting fires", "A", 3));
        return offenses;
    }
}

