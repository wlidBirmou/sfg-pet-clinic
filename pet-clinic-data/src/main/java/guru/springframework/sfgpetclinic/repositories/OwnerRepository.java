package guru.springframework.sfgpetclinic.repositories;

import guru.springframework.sfgpetclinic.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OwnerRepository extends JpaRepository<Owner,Long> {

    public Optional<Owner> findByLastName(String lastName);
    public List<Owner> findAllByLastNameContains(String lastName);
}
