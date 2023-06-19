package guru.springframework.sfgpetclinic.model;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name="vets")
public class Vet  extends Person{

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name = "vet-specialties", joinColumns = @JoinColumn(name = "vet_id"),inverseJoinColumns = @JoinColumn(name="speciality_id"))
    private Set<Speciality> specialities=new LinkedHashSet<>();


    public Set<Speciality> getSpecialities() {
        return specialities;
    }

    public void setSpecialities(Set<Speciality> specialities) {
        this.specialities = specialities;
    }
}
