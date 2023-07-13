package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Profile({"default","map"})
public class OwnerMapService extends AbstractMapService<Owner,Long > implements OwnerService{

    private PetService petService;
    private PetTypeService petTypeService;


    @Autowired
    public OwnerMapService(PetService petService, PetTypeService petTypeService) {
        this.petService = petService;
        this.petTypeService=petTypeService;
    }

    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Owner save(Owner owner) {
        if(owner !=null){
           if(owner.getPets().size()!=0) owner.getPets().forEach(p -> {
               if (p.getPetType()!=null) {
                   if (p.getPetType().getId()==null) {
                       this.petTypeService.save(p.getPetType());
                   }
               } else {
                   throw new RuntimeException("PetType is required");
               }
               if (p.getId()==null)this.petService.save(p);
           });
        }else {
            return null;
        }
        return super.save(owner);
    }

    @Override
    public void delete(Owner owner) {
        super.delete(owner);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public Owner findByLastName(String lastName) {
        Owner owner=this.map.values().stream().filter(o -> o.getLastName().equals(lastName)).findFirst().orElse(null);
        return owner;
    }

    @Override
    public List<Owner> findAllByLastNameContains(String lastName) {
        return this.findAll().stream().filter(o -> o.getLastName().contains(lastName)).collect(Collectors.toList());
    }

}
