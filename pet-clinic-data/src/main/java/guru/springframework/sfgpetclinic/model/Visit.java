package guru.springframework.sfgpetclinic.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Visit extends BaseEntity{

    @Column(name="date")
    private LocalDate date;
    @Column(name="description")
    private String description;
    @ManyToOne(fetch = FetchType.EAGER)
    private Pet pet;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }
}
