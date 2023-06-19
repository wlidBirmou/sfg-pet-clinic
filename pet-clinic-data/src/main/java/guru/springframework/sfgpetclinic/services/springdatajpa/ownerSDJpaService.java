package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.repositories.OwnerRepository;
import guru.springframework.sfgpetclinic.repositories.PetRepository;
import guru.springframework.sfgpetclinic.repositories.PetTypeRepository;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class ownerSDJpaService implements OwnerService {


    private final OwnerRepository ownerRepository;
    private final PetRepository petRepository;
    private final PetTypeRepository petTypeRepository;


    @Autowired
    public ownerSDJpaService(OwnerRepository ownerRepository, PetRepository petRepository, PetTypeRepository petTypeRepository) {
        this.ownerRepository = ownerRepository;
        this.petRepository = petRepository;
        this.petTypeRepository = petTypeRepository;
    }

    @Override
    public Owner findById(Long aLong) {
        Optional<Owner> ownerOptional=this.ownerRepository.findById(aLong);
        return (ownerOptional.isPresent()?ownerOptional.get():null);
    }

    @Override
    public Owner save(Owner owner) {
        return this.ownerRepository.save(owner);

    }

    @Override
    public Set<Owner> findAll() {
        Set<Owner> owners=new LinkedHashSet<>();
        this.ownerRepository.findAll().forEach(owners::add);
        return owners;
    }

    @Override
    public void delete(Owner object) {
        this.ownerRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        this.ownerRepository.deleteById(aLong);
    }

    @Override
    public Owner findByLastName(String lastName) {
        return this.ownerRepository.findByLastName(lastName);
    }
}
