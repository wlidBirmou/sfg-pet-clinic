package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.exceptions.NotFoundException;
import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.repositories.PetTypeRepository;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;

@Service
@Profile("springdatajpa")
@Slf4j
@AllArgsConstructor
public class PetTypeSDJpaService implements PetTypeService {

    private final PetTypeRepository petTypeRepository;
    private final MessageSource messageSource;


    @Override
    public PetType findById(Long id) {
        Object[] exceptionArguments={this.messageSource.getMessage("petType",new Object[0], Locale.US),id};
        return this.petTypeRepository.findById(id).orElseThrow(()-> new NotFoundException(messageSource.getMessage("entityNotExistById",exceptionArguments,
                Locale.US)));
    }

    @Override
    public PetType save(PetType petType) {
        return this.petTypeRepository.save(petType);
    }

    @Override
    public Set<PetType> findAll() {
        Set<PetType> petTypes=new LinkedHashSet<>();
        this.petTypeRepository.findAll().forEach(petTypes::add);
        return petTypes;
    }

    @Override
    public void delete(PetType object) {
        this.petTypeRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        this.petTypeRepository.deleteById(id);
    }
}
