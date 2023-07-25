package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.repositories.OwnerRepository;
import guru.springframework.sfgpetclinic.repositories.PetRepository;
import guru.springframework.sfgpetclinic.repositories.PetTypeRepository;
import guru.springframework.sfgpetclinic.services.OwnerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.core.Local;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import guru.springframework.sfgpetclinic.exceptions.NotFoundException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

@Service
@AllArgsConstructor
@Profile("springdatajpa")
@Slf4j
public class OwnerSDJpaService implements OwnerService {


    private final OwnerRepository ownerRepository;
    private final MessageSource messageSource;


    @Override
    public Owner findById(Long id) {
        Object[] exceptionArguments={this.messageSource.getMessage("owner",new Object[0], Locale.US),id};
        return  this.ownerRepository.findById(id).orElseThrow(()-> new NotFoundException(messageSource.getMessage("entityNotExistById",exceptionArguments,
        Locale.US)));
    }

    @Override
    public Owner save(Owner owner) {
        log.debug("Owner saved");
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
    public void deleteById(Long id) {
        this.ownerRepository.deleteById(id);
    }

    @Override
    public Owner findByLastName(String lastName) {
        Object[] exceptionArguments={this.messageSource.getMessage("owner",new Object[0], Locale.US),lastName};
        return this.ownerRepository.findByLastName(lastName).orElseThrow(()-> new NotFoundException(messageSource.getMessage("entityNotExistByLastName",exceptionArguments,
                Locale.US)));
    }

    @Override
    public List<Owner> findAllByLastNameContains(String lastName) {
        if(lastName!=null){
            List<Owner> owners=this.ownerRepository.findAllByLastNameContains(lastName);
            return owners;
        } else {
            throw new RuntimeException("parameter lastName is null");
        }
    }

}
