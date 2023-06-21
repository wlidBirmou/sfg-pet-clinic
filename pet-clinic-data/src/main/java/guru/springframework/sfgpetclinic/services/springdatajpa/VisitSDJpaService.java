package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.repositories.VisitRepository;
import guru.springframework.sfgpetclinic.services.VisitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;


@Service
@Profile("springdatajpa")
@Slf4j
public class VisitSDJpaService  implements VisitService {

    private final VisitRepository visitRepository;

    public VisitSDJpaService(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }

    @Override
    public Visit findById(Long aLong) {
        return this.visitRepository.findById(aLong).orElse(null);
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
    public void deleteById(Long aLong) {
        this.visitRepository.deleteById(aLong);
    }
}
