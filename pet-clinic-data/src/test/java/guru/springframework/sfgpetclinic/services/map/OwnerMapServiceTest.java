package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.map.OwnerMapService;
import guru.springframework.sfgpetclinic.services.map.PetMapService;
import guru.springframework.sfgpetclinic.services.map.PetTypeMapService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class OwnerMapServiceTest {


    private OwnerMapService ownerMapService;

    @BeforeEach
    void setUp() {
        this.ownerMapService=new OwnerMapService(new PetMapService(),new PetTypeMapService());
        Owner owner1=Owner.builder().id(1l).firstName("Abderrahim").lastName("LAAKAB").address("123 Sidi yahia")
                .city("Algiers").telephone("0014383720520").build();
        Owner owner2=Owner.builder().id(2l).firstName("Said").lastName("MOUHOUNE").address("123 WAHAT, Hydra")
                .city("Algiers").telephone("00213772847864").build();
        this.ownerMapService.save(owner1);
        this.ownerMapService.save(owner2);
    }

    @Test
    void findAll(){
        assertEquals(2, this.ownerMapService.findAll().size());
    }

    @Test
    void findById() {
          Owner owner=this.ownerMapService.findById(1l);
          assertNotNull(owner);
          assertEquals("Abderrahim", owner.getFirstName());
    }

    @Test
    void findByIdIsNull() {
        Owner owner=this.ownerMapService.findById(10l);
        assertNull(owner);

    }

    @Test
    void save() {
        assertEquals(2,this.ownerMapService.findAll().size());
    }

    @Test
    void saveNull() {
        Owner owner=this.ownerMapService.save(null);
        assertNull(owner);
    }

    @Test
    void saveNotNull() {
        Owner owner=this.ownerMapService.save(Owner.builder().build());
        assertNotNull(owner);
        assertNotNull(owner.getId());
    }

    @Test
    void saveExistingId(){

        Owner savedOwner=this.ownerMapService.save(Owner.builder().id(1l).firstName("existingId").build());
        assertEquals("existingId", this.ownerMapService.findById(1l).getFirstName());
    }

    @Test
    void delete() {
        this.ownerMapService.delete(this.ownerMapService.findById(1l));
        assertNull(this.ownerMapService.findById(1l));
    }

    @Test
    void deleteById() {
        this.ownerMapService.deleteById(1l);
        Owner owner=this.ownerMapService.findById(1l);
        assertNull(owner);
    }

    @Test
    void findByLastName() {
        Owner owner=this.ownerMapService.findByLastName("LAAKAB");
        assertEquals(1l,owner.getId());
    }



}