package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.repositories.OwnerRepository;
import guru.springframework.sfgpetclinic.repositories.PetRepository;
import guru.springframework.sfgpetclinic.repositories.PetRepository;
import guru.springframework.sfgpetclinic.services.PetService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
@Slf4j
@AllArgsConstructor
public class PetSDJpaService implements PetService {

    private final PetRepository petRepository;


    @Override
    public Pet findById(Long aLong) {
        return this.petRepository.findById(aLong).orElse(null);
    }

    @Override
    public Pet save(Pet pet) {

        return this.petRepository.save(pet);
    }

    @Override
    public Set<Pet> findAll() {
        Set<Pet> pets=new LinkedHashSet<>();
        this.petRepository.findAll().forEach(pets::add);
        return pets;
    }

    @Override
    public void delete(Pet object) {
        this.petRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        this.petRepository.deleteById(aLong);
    }

    @Override
    public void updatePetInOwner(Owner owner, Pet pet) {
        owner.getPets().parallelStream().filter(p -> p.getId().equals(pet.getId())).map(p ->
                {
                    p.setName(pet.getName());
                    p.setBirthDate(pet.getBirthDate());
                    p.setPetType(pet.getPetType());
                    p.setVisits(pet.getVisits());
                    return p;
                }
        ).findFirst().orElseThrow(()-> new RuntimeException());
    }
}
