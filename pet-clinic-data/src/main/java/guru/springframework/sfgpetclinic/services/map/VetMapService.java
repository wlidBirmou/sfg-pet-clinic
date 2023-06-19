package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.SpecialityService;
import guru.springframework.sfgpetclinic.services.VetService;
import org.springframework.stereotype.Service;


@Service
public class VetMapService extends AbstractMapService<Vet, Long> implements VetService {

    private final SpecialityService specialityService;

    public VetMapService(SpecialityService specialityService) {
        this.specialityService = specialityService;
    }


    @Override
    public Vet findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Vet save(Vet vet) {
        if(vet !=null){
            if(vet.getSpecialities().size()!=0) vet.getSpecialities().forEach(spe ->
            {
                if(spe!=null) {
                    if(spe.getId()==null){
                        Speciality savedSpeciality=this.specialityService.save(spe);
                        spe.setId(savedSpeciality.getId());
                    }
                }
            });
        }else {
            return null;
        }
        return super.save(vet);

    }

    @Override
    public void delete(Vet vet) {
        super.delete(vet);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }


}