package guru.springframework.sfgpetclinic.model;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name="speciality")
public class Speciality extends BaseEntity {

    @Column(name="description")
    private String description;

    @ManyToMany(mappedBy = "specialities")
    private Set<Vet> vets=new LinkedHashSet<>();

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
