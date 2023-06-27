package guru.springframework.sfgpetTypeclinic.services.map;

import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.services.map.PetTypeMapService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PetTypeTypeMapServiceTest {
    private PetTypeMapService petTypeMapService;

    @BeforeEach
    void setUp() {
        this.petTypeMapService=new PetTypeMapService();
        PetType petType1=PetType.builder().id(1l).name("cat").build();
        PetType petType2=PetType.builder().id(2l).name("dog").build();
        this.petTypeMapService.save(petType1);
        this.petTypeMapService.save(petType2);
    }

    @Test
    void findAll(){
        assertEquals(2, this.petTypeMapService.findAll().size());
    }

    @Test
    void findById() {
        PetType petType=this.petTypeMapService.findById(1l);
        assertNotNull(petType);
        assertEquals(1l, petType.getId());
    }

    @Test
    void findByIdIsNull() {
        PetType petType=this.petTypeMapService.findById(10l);
        assertNull(petType);
    }

    @Test
    void save() {

        assertEquals(2,this.petTypeMapService.findAll().size());
    }

    @Test
    void saveNull() {
        PetType petType=this.petTypeMapService.save(null);
        assertNull(petType);

    }

    @Test
    void saveNotNull() {
        PetType petType=this.petTypeMapService.save(PetType.builder().build());
        assertNotNull(petType);
        assertNotNull(petType.getId());
    }

    @Test
    void saveExistingId(){
        PetType savedPetType=this.petTypeMapService.save(PetType.builder().id(1l).name("existingPetType").build());
        assertEquals("existingPetType", this.petTypeMapService.findById(1l).getName());
    }

    @Test
    void delete() {
        this.petTypeMapService.delete(this.petTypeMapService.findById(1l));
        assertNull(this.petTypeMapService.findById(1l));
    }

    @Test
    void deleteById() {
        this.petTypeMapService.deleteById(1l);
        PetType petType=this.petTypeMapService.findById(1l);
        assertNull(petType);
    }
}