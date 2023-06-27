package guru.springframework.sfgpetclinic.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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


    @OneToMany(mappedBy = "pet", cascade = CascadeType.ALL)
    private Set<Visit> visits=new LinkedHashSet<>();

    @Builder
    public Pet(Long id, String name, LocalDate birthDate) {
        super(id);
        this.name = name;
        this.birthDate = birthDate;
    }
}
