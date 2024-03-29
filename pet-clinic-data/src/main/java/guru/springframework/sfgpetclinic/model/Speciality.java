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
@Table(name="speciality")
public class Speciality extends BaseEntity {

    @Column(name="description")
    private String description;


    @Builder
    public Speciality(Long id, String description) {
        super(id);
        this.description = description;
    }
}
