package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Speciality;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpecialityMapServiceTest {
    private SpecialityMapService specialityMapService;

    @BeforeEach
    void setUp() {
        this.specialityMapService=new SpecialityMapService();
        Speciality speciality1=Speciality.builder().id(1l).description("surgery").build();
        Speciality speciality2=Speciality.builder().id(2l).description("dentistery").build();
        this.specialityMapService.save(speciality1);
        this.specialityMapService.save(speciality2);
    }

    @Test
    void findAll(){
        assertEquals(2, this.specialityMapService.findAll().size());
    }

    @Test
    void findById() {
        Speciality speciality=this.specialityMapService.findById(1l);
        assertNotNull(speciality);
        assertEquals(1l, speciality.getId());
    }

    @Test
    void findByIdIsNull() {
        Speciality speciality=this.specialityMapService.findById(10l);
        assertNull(speciality);
    }

    @Test
    void save() {

        assertEquals(2,this.specialityMapService.findAll().size());
    }

    @Test
    void saveNull() {
        Speciality speciality=this.specialityMapService.save(null);
        assertNull(speciality);

    }

    @Test
    void saveNotNull() {
        Speciality speciality=this.specialityMapService.save(Speciality.builder().build());
        assertNotNull(speciality);
        assertNotNull(speciality.getId());
    }

    @Test
    void saveExistingId(){
        Speciality savedSpeciality=this.specialityMapService.save(Speciality.builder().id(1l).description("existingSpeciality").build());
        assertEquals("existingSpeciality", this.specialityMapService.findById(1l).getDescription());
    }

    @Test
    void delete() {
        this.specialityMapService.delete(this.specialityMapService.findById(1l));
        assertNull(this.specialityMapService.findById(1l));
    }

    @Test
    void deleteById() {
        this.specialityMapService.deleteById(1l);
        Speciality speciality=this.specialityMapService.findById(1l);
        assertNull(speciality);
    }
}