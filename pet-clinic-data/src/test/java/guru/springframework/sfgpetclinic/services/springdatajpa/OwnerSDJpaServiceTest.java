package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.repositories.OwnerRepository;
import guru.springframework.sfgpetclinic.repositories.PetRepository;
import guru.springframework.sfgpetclinic.repositories.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {

    @Mock
    private  OwnerRepository ownerRepository;
    @Mock
    private  PetRepository petRepository;
    @Mock
    private  PetTypeRepository petTypeRepository;

    @InjectMocks
    OwnerSDJpaService ownerSDJpaService;
    Owner owner;
    @BeforeEach
    void setUp() {
        this.owner = Owner.builder().id(1l).firstName("Alan").lastName("Smith").address("123 fisi").city("Algiers").telephone("14e340239").build();
        this.ownerSDJpaService.save(owner);

    }

    @Test
    void findById() {
        when(this.ownerRepository.findById(1l)).thenReturn(Optional.of(this.owner));
        Owner returnedOwner=this.ownerSDJpaService.findById(1l);
        assertNotNull(returnedOwner);
        assertEquals(1l,returnedOwner.getId());
    }

    @Test
    void save() {
        when(this.ownerRepository.save(eq(owner))).thenReturn(owner);
        Owner savedOwner=this.ownerSDJpaService.save(owner);
        assertNotNull(savedOwner);
        assertEquals(savedOwner,owner);
    }

    @Test
    void findAll() {
        List<Owner> list=new ArrayList<>();
        list.add(owner);

        when(this.ownerRepository.findAll()).thenReturn(list);

        Set<Owner> owners=this.ownerSDJpaService.findAll();
        assertNotNull(owners);
        assertEquals(1,owners.size());
    }

    @Test
    void delete() {
        this.ownerSDJpaService.delete(owner);
        verify(this.ownerRepository,times(1)).delete(any());
     }

    @Test
    void deleteById() {
        this.ownerSDJpaService.deleteById(1l);
        verify(this.ownerRepository,times(1)).deleteById(any());
    }

    @Test
    void findByLastName() {
        when(ownerRepository.findByLastName("Smith")).thenReturn(owner);
        Owner smith=this.ownerSDJpaService.findByLastName("Smith");
        assertNotNull(smith);
        assertEquals("Smith",smith.getLastName() );
        verify(this.ownerRepository,times(1)).findByLastName(any());
    }

    @Test
    void findAllByLastName(){
        List<Owner> owners=new ArrayList<>();
        owners.add(Owner.builder().id(1l).lastName("Laakab").firstName("Abderrahim").telephone("268466556").city("CDN").build());
        owners.add(Owner.builder().id(2l).lastName("Laakab").firstName("Mohamed").telephone("268466556").city("NDG").build());

        when(this.ownerRepository.findAllByLastNameContains(anyString())).thenReturn(owners);

        List<Owner> ownersResult=this.ownerSDJpaService.findAllByLastNameContains("Laakab");
        assertEquals(owners.size(),ownersResult.size());

        verify(this.ownerRepository,times(1)).findAllByLastNameContains(anyString());
    }

    @Test
    void findAllByLastNameWhenEmpty (){
        List<Owner> owners=new ArrayList<>();
        when(this.ownerRepository.findAllByLastNameContains(anyString())).thenReturn(owners);
        List<Owner> ownersResult=this.ownerSDJpaService.findAllByLastNameContains("");
        assertEquals(owners.size(),ownersResult.size());
        verify(this.ownerRepository,times(1)).findAllByLastNameContains(anyString());
    }



}