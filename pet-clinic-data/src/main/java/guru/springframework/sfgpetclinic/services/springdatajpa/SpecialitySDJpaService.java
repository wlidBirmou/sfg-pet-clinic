package guru.springframework.sfgpetclinic.services.springdatajpa;


import guru.springframework.sfgpetclinic.exceptions.NotFoundException;
import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.services.SpecialityService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import guru.springframework.sfgpetclinic.repositories.SpecialityRepository;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Set;



@Service
@Profile("springdatajpa")
@Slf4j
@AllArgsConstructor
public class SpecialitySDJpaService implements SpecialityService {

    private final SpecialityRepository specialityRepository;
    private final MessageSource messageSource;


    @Override
    public Speciality findById(Long id) {
        Object[] exceptionArguments={this.messageSource.getMessage("speciality",new Object[0], Locale.US),id};
        return this.specialityRepository.findById(id).orElseThrow(()-> new NotFoundException(messageSource.getMessage("entityNotExistById",exceptionArguments,
                Locale.US)));
    }

    @Override
    public Speciality save(Speciality speciality) {
        return this.specialityRepository.save(speciality);
    }

    @Override
    public Set<Speciality> findAll() {
        Set<Speciality> specialties=new LinkedHashSet<>();
        this.specialityRepository.findAll().forEach(specialties::add);
        return specialties;
    }

    @Override
    public void delete(Speciality object) {
        this.specialityRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        this.specialityRepository.deleteById(id);
    }
}
