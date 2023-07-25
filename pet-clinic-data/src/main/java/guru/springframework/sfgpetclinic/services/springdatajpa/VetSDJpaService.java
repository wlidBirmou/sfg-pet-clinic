package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.exceptions.NotFoundException;
import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.repositories.VetRepository;
import guru.springframework.sfgpetclinic.services.VetService;
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
public class VetSDJpaService implements VetService {

    private final VetRepository vetRepository;
    private final MessageSource  messageSource;


    @Override
    public Vet findById(Long id) {
        Object[] exceptionArguments={this.messageSource.getMessage("vet",new Object[0], Locale.US),id};
        return this.vetRepository.findById(id).orElseThrow(()-> new NotFoundException(messageSource.getMessage("entityNotExistById",exceptionArguments,
                Locale.US)));
    }

    @Override
    public Vet save(Vet vet) {
        return this.vetRepository.save(vet);
    }

    @Override
    public Set<Vet> findAll() {
        Set<Vet> vets=new LinkedHashSet<>();
        this.vetRepository.findAll().forEach(vets::add);
        return vets;
    }

    @Override
    public void delete(Vet object) {
        this.vetRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        this.vetRepository.deleteById(id);
    }
}
