package guru.springframework.sfgpetclinic.model;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name="types")
public class PetType  extends BaseEntity{

    @Column(name="name")
    private String name;

    @OneToMany(mappedBy = "petType", cascade = CascadeType.ALL )
    Set<Pet> pets=new LinkedHashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Pet> getPets() {
        return pets;
    }

    public void setPets(Set<Pet> pets) {
        this.pets = pets;
    }
}
