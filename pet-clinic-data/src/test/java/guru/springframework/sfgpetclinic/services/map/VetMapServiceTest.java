package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Vet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VetMapServiceTest {
    private VetMapService vetMapService;

    @BeforeEach
    void setUp() {

        this.vetMapService=new VetMapService(new SpecialityMapService());
        Vet vet1=Vet.builder().id(1l).firstName("Moncef").lastName("Bedjaoui").build();
        Vet vet2=Vet.builder().id(2l).firstName("Younes").lastName("Asma").build();
        this.vetMapService.save(vet1);
        this.vetMapService.save(vet2);
    }

    @Test
    void findAll(){
        assertEquals(2, this.vetMapService.findAll().size());
    }

    @Test
    void findById() {
        Vet vet=this.vetMapService.findById(1l);
        assertNotNull(vet);
        assertEquals(1l, vet.getId());
    }

    @Test
    void findByIdIsNull() {
        Vet vet=this.vetMapService.findById(10l);
        assertNull(vet);
    }

    @Test
    void save() {

        assertEquals(2,this.vetMapService.findAll().size());
    }

    @Test
    void saveNull() {
        Vet vet=this.vetMapService.save(null);
        assertNull(vet);

    }

    @Test
    void saveNotNull() {
        Vet vet=this.vetMapService.save(Vet.builder().build());
        assertNotNull(vet);
        assertNotNull(vet.getId());
    }

    @Test
    void saveExistingId(){
        Vet savedVet=this.vetMapService.save(Vet.builder().id(1l).lastName("existingVet").build());
        assertEquals("existingVet", this.vetMapService.findById(1l).getLastName());
    }

    @Test
    void delete() {
        this.vetMapService.delete(this.vetMapService.findById(1l));
        assertNull(this.vetMapService.findById(1l));
    }

    @Test
    void deleteById() {
        this.vetMapService.deleteById(1l);
        Vet vet=this.vetMapService.findById(1l);
        assertNull(vet);
    }
}