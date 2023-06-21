package guru.springframework.sfgpetclinic.services.springdatajpa;


import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.services.SpecialityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import guru.springframework.sfgpetclinic.repositories.SpecialityRepository;
import java.util.LinkedHashSet;
import java.util.Set;



@Service
@Profile("springdatajpa")
@Slf4j
public class SpecialitySDJpaService implements SpecialityService {

    private final SpecialityRepository specialityRepository;

    public SpecialitySDJpaService(SpecialityRepository specialityRepository) {
        this.specialityRepository = specialityRepository;
    }

    @Override
    public Speciality findById(Long aLong) {
        return this.specialityRepository.findById(aLong).orElse(null);
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
    public void deleteById(Long aLong) {
        this.specialityRepository.deleteById(aLong);
    }
}
