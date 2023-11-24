package dto.tm;

import java.time.LocalDate;

public class ItemTable {
    private String id;
    private String name;
    private double price;
    private String category;
    private LocalDate date;
    private String description;

    public ItemTable() {
    }

    public ItemTable(String id, String name, double price, String category, LocalDate date, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.date = date;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = LocalDate.parse(date);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
