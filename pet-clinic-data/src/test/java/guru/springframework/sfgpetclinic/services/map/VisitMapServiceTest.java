package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.Visit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class VisitMapServiceTest {
    private VisitMapService visitMapService;

    @BeforeEach
    void setUp() {
        this.visitMapService=new VisitMapService();
        Visit visit1=Visit.builder().id(1l).description("1 visit").date(LocalDate.now()).build();
        Visit visit2=Visit.builder().id(2l).description("2 eme visit").date(LocalDate.now()).build();
        Pet pet1=Pet.builder().id(1l).name("Sisi").build();
        Pet pet2=Pet.builder().id(2l).name("Chetrane").build();
        pet1.setOwner(Owner.builder().id(1l).firstName("abderrahim").build());
        pet2.setOwner(Owner.builder().id(2l).firstName("Said").build());
        visit1.setPet(pet1);
        visit2.setPet(pet2);
        this.visitMapService.save(visit1);
        this.visitMapService.save(visit2);
    }

    @Test
    void findAll(){
        assertEquals(2, this.visitMapService.findAll().size());
    }

    @Test
    void findById() {
        Visit visit=this.visitMapService.findById(1l);
        assertNotNull(visit);
        assertEquals(1l, visit.getId());
    }

    @Test
    void findByIdIsNull() {
        Visit visit=this.visitMapService.findById(10l);
        assertNull(visit);
    }

    @Test
    void save() {

        assertEquals(2,this.visitMapService.findAll().size());

    }

    @Test
    void saveNull() {
        Visit visit=this.visitMapService.save(null);
        assertNull(visit);
    }

    @Test
    void saveNotNull() {

        Visit visit=Visit.builder().build();
        Pet pet=Pet.builder().id(1l).name("Sisi").build();
        Owner owner=Owner.builder().id(2l).firstName("Said").build();
        pet.setOwner(owner);
        visit.setPet(pet);
        Visit savedVisit=this.visitMapService.save(visit);
        assertNotNull(savedVisit);
        assertNotNull(savedVisit.getId());
    }

    @Test
    void saveExistingId(){
        Visit visit=Visit.builder().id(1l).description("existingVisit").build();
        Pet pet=Pet.builder().id(1l).name("Sisi").build();
        Owner owner=Owner.builder().id(2l).firstName("Said").build();
        pet.setOwner(owner);
        visit.setPet(pet);
        Visit savedVisit=this.visitMapService.save(visit);
        assertEquals("existingVisit", this.visitMapService.findById(1l).getDescription());
    }

    @Test
    void delete() {
        this.visitMapService.delete(this.visitMapService.findById(1l));
        assertNull(this.visitMapService.findById(1l));
    }

    @Test
    void deleteById() {
        this.visitMapService.deleteById(1l);
        Visit visit=this.visitMapService.findById(1l);
        assertNull(visit);
    }
}