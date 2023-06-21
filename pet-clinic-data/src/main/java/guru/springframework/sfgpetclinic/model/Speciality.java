package guru.springframework.sfgpetclinic.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="speciality")
public class Speciality extends BaseEntity {

    @Column(name="description")
    private String description;

    @ManyToMany(mappedBy = "specialities")
    private Set<Vet> vets=new LinkedHashSet<>();

}
