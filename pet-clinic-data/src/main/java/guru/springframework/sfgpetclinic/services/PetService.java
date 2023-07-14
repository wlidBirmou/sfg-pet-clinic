package guru.springframework.sfgpetclinic.services;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;

public interface PetService extends CrudService<Pet,Long> {

    public void updatePetInOwner(Owner owner, Pet pet);
}
