package guru.springframework.sfgpetclinic.services;

import guru.springframework.sfgpetclinic.model.Owner;

import java.util.List;

public interface OwnerService extends CrudService<Owner,Long> {


    public Owner findByLastName(String lastName);
    public List<Owner> findAllByLastNameContains(String lastName);

}
