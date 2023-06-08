package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.services.PetService;
import org.springframework.stereotype.Service;

@Service
public class PetServiceMap extends AbstractMapService<Pet, Long> implements PetService {

    @Override
    public Pet findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Pet save(Pet pet) {
        return super.save( pet);
    }

    @Override
    public void delete(Pet owner) {
        super.deleteByObject(owner);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}