package cat.itacademy.s04.t02.n01.model;

import jakarta.persistence.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Fruit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Fruit name cannot be empty")
    @Size(max = 50, message = "Fruit name cannot exceed 50 characters")
    private String name;

    @Min(value = 1, message = "Fruit weight must be positive")
    private int weight;

    public Fruit() {
    }

    public Fruit(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}