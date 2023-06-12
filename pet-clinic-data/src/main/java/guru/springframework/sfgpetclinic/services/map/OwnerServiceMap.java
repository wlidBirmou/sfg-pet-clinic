package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import org.springframework.stereotype.Service;

@Service
public class OwnerServiceMap extends AbstractMapService<Owner,Long > implements OwnerService{

    private PetService petService;
    private PetTypeService petTypeService;


    public OwnerServiceMap(PetService petService,PetTypeService petTypeService) {
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
        this.deleteById(id);
    }

    @Override
    public Owner findByLastName(String lastName) {
        return null;
    }
}
