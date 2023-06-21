package guru.springframework.sfgpetclinic.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="owners")
public class Owner extends Person {

    @Column(name="address")
    private String address;
    @Column(name="city")
    private String city;
    @Column(name="telephone")
    private String telephone;
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    Set<Pet> pets=new LinkedHashSet<>();

    @Builder
    public Owner(Long id, String firstName, String lastName, String address, String city, String telephone) {
        super(id, firstName, lastName);
        this.address = address;
        this.city = city;
        this.telephone = telephone;
    }
}
