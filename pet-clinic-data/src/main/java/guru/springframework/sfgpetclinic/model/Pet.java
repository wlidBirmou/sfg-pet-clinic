package guru.springframework.sfgpetclinic.model;

import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@Table(name="pets")
public class Pet  extends BaseEntity {


    @Column(name="name")
    private String name;
    @Column(name="birth_date")
    private LocalDate birthDate;

    @ManyToOne(fetch =FetchType.EAGER)
    @JoinColumn(name="pet_type_id")
    private PetType petType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id")
    private Owner owner;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public PetType getPetType() {
        return petType;
    }

    public void setPetType(PetType petType) {
        this.petType = petType;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
}
