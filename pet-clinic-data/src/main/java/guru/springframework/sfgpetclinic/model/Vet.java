package guru.springframework.sfgpetclinic.model;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

public class Vet  extends Person{

    private Long specialityId;
    private Set<Speciality> specialities=new LinkedHashSet<>();


    public Long getSpecialityId() {
        return specialityId;
    }

    public void setSpecialityId(Long specialityId) {
        this.specialityId = specialityId;
    }

    public Set<Speciality> getSpecialities() {
        return specialities;
    }

    public void setSpecialities(Set<Speciality> specialities) {
        this.specialities = specialities;
    }
}
