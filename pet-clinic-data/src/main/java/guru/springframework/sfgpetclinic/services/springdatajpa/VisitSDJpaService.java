package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.exceptions.NotFoundException;
import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.repositories.VisitRepository;
import guru.springframework.sfgpetclinic.services.VisitService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Set;


@Service
@Profile("springdatajpa")
@Slf4j
@AllArgsConstructor
public class VisitSDJpaService  implements VisitService {

    private final VisitRepository visitRepository;
    private final MessageSource messageSource;


    @Override
    public Visit findById(Long id) {
        Object[] exceptionArguments={this.messageSource.getMessage("visit",new Object[0], Locale.US),id};
        return this.visitRepository.findById(id).orElseThrow(()-> new NotFoundException(messageSource.getMessage("entityNotExistById",exceptionArguments,
                Locale.US)));
    }

    @Override
    public Visit save(Visit visit) {
        return this.visitRepository.save(visit);
    }

    @Override
    public Set<Visit> findAll() {
        Set<Visit> visits=new LinkedHashSet<>();
        this.visitRepository.findAll().forEach(visits::add);
        return visits;
    }

    @Override
    public void delete(Visit object) {

        this.visitRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {

        this.visitRepository.deleteById(id);
    }
}
