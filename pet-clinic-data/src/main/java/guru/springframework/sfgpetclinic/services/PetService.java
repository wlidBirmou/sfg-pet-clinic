package guru.springframework.sfgpetclinic.services;

import guru.springframework.sfgpetclinic.model.Pet;

import java.util.Set;

public interface PetService {
    public Pet findById(Long id);
    public Pet save(Pet Pet);
    public Set<Pet> findAll();
    public Pet findByLastName(String lastName);
}
