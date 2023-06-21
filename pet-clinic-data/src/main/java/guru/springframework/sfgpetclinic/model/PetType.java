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
@Table(name="types")
public class PetType  extends BaseEntity{

    @Column(name="name")
    private String name;

    @OneToMany(mappedBy = "petType", cascade = CascadeType.ALL )
    Set<Pet> pets=new LinkedHashSet<>();

}
