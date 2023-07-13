package guru.springframework.sfgpetclinic.repositories;

import guru.springframework.sfgpetclinic.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OwnerRepository extends JpaRepository<Owner,Long> {

    public Owner findByLastName(String lastName);
    public List<Owner> findAllByLastNameContains(String lastName);
}
