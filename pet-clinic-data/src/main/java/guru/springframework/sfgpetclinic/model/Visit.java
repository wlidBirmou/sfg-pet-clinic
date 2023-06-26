package guru.springframework.sfgpetclinic.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="visits")
public class Visit extends BaseEntity{

    @Column(name="date")
    private LocalDate date;
    @Column(name="description")
    private String description;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(columnDefinition = "pet_id")
    private Pet pet;

    @Builder
    public Visit(Long id, LocalDate date, String description) {
        super(id);
        this.date = date;
        this.description = description;
    }
}
