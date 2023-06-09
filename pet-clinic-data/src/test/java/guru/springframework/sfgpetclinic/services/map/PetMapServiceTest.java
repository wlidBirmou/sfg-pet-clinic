package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Pet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class PetMapServiceTest {


    private PetMapService petMapService;

    @BeforeEach
    void setUp() {
        this.petMapService=new PetMapService();
        Pet pet1=Pet.builder().id(1l).name("sisi").birthDate(LocalDate.now()).build();
        Pet pet2=Pet.builder().id(2l).name("chetrane").birthDate(LocalDate.now()).build();
        this.petMapService.save(pet1);
        this.petMapService.save(pet2);
    }

    @Test
    void findAll(){
        assertEquals(2, this.petMapService.findAll().size());
    }

    @Test
    void findById() {
        Pet pet=this.petMapService.findById(1l);
        assertNotNull(pet);
        assertEquals(1l, pet.getId());
    }

    @Test
    void findByI1dIsNull() {
        Pet pet=this.petMapService.findById(10l);
        assertNull(pet);
    }

    @Test
    void save() {

        assertEquals(2,this.petMapService.findAll().size());
    }

    @Test
    void saveNull() {
        Pet pet=this.petMapService.save(null);
        assertNull(pet);

    }

    @Test
    void saveNotNull() {
        Pet pet=this.petMapService.save(Pet.builder().build());
        assertNotNull(pet);
        assertNotNull(pet.getId());
    }

    @Test
    void saveExistingId(){
        Pet savedPet=this.petMapService.save(Pet.builder().id(1l).name("existingPet").build());
        assertEquals("existingPet", this.petMapService.findById(1l).getName());
    }

    @Test
    void delete() {
        this.petMapService.delete(this.petMapService.findById(1l));
        assertNull(this.petMapService.findById(1l));
    }

    @Test
    void deleteById() {
        this.petMapService.deleteById(1l);
        Pet pet=this.petMapService.findById(1l);
        assertNull(pet);
    }

}