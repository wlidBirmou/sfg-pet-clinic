package guru.springframework.sfgpetclinic.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="vets")
public class Vet  extends Person{

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name = "vet-specialties", joinColumns = @JoinColumn(name = "vet_id"),inverseJoinColumns = @JoinColumn(name="speciality_id"))
    private Set<Speciality> specialities=new LinkedHashSet<>();

    @Builder
    public Vet(Long id, String firstName, String lastName) {
        super(id, firstName, lastName);
    }
}
