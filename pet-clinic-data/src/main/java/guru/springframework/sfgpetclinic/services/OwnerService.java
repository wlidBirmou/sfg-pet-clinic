package guru.springframework.sfgpetclinic.services;

import guru.springframework.sfgpetclinic.model.Owner;

import java.util.Set;

public interface OwnerService {

    public Owner findById(Long id);
    public Owner save(Owner owner);
    public Set<Owner> findAll();
    public Owner findByLastName(String lastName);
}