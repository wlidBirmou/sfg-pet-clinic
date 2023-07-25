package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.exceptions.NotFoundException;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.repositories.PetRepository;
import guru.springframework.sfgpetclinic.repositories.PetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class PetSDJpaServiceTest {

    @Mock
    private PetRepository petRepository;
    @Mock
    private MessageSource messageSource;
    @InjectMocks
    private PetSDJpaService petSDJpaService;
    private Pet pet;
    @BeforeEach
    void setUp() {
        this.pet = Pet.builder().id(1l).name("Sisi").birthDate(LocalDate.now()).build();

    }

    @Test
    void testFindById() {
        when(this.petRepository.findById(1l)).thenReturn(Optional.of(this.pet));
        when(this.messageSource.getMessage(anyString(), any(Object[].class) , any(Locale.class))).thenReturn("pet");

        Pet returnedPet=this.petSDJpaService.findById(1l);
        assertNotNull(returnedPet);
        assertEquals(1l,returnedPet.getId());
        verify(this.petRepository,times(1)).findById(anyLong());
        verify(this.messageSource,times(1)).getMessage(anyString(),any(Object[].class),any(Locale.class));
    }

    @Test
     void testFindByIdNotFound() {
        when(this.petRepository.findById(1l)).thenReturn(Optional.empty());
        when(this.messageSource.getMessage(anyString(), any(Object[].class) , any(Locale.class))).thenReturn("pet");


        assertThrows(NotFoundException.class,()-> this.petSDJpaService.findById(1l));
        verify(this.petRepository,times(1)).findById(anyLong());
        verify(this.messageSource,times(2)).getMessage(anyString(),any(Object[].class),any(Locale.class));
    }

    @Test
    void testSave() {
        when(this.petRepository.save(eq(pet))).thenReturn(pet);
        Pet savedPet=this.petSDJpaService.save(pet);
        assertNotNull(savedPet);
        assertEquals(savedPet,pet);
        verify(this.petRepository,times(1)).save(any());
    }

    @Test
    void testFindAll() {
        List<Pet> list=new ArrayList<>();
        list.add(pet);

        when(this.petRepository.findAll()).thenReturn(list);

        Set<Pet> pets=this.petSDJpaService.findAll();
        assertNotNull(pets);
        assertEquals(1,pets.size());
        verify(this.petRepository,times(1)).findAll();
    }

    @Test
    void testDelete() {
        this.petSDJpaService.delete(pet);
        verify(this.petRepository,times(1)).delete(any());
    }

    @Test
    void testDeleteById() {
        this.petSDJpaService.deleteById(1l);
        verify(this.petRepository,times(1)).deleteById(any());
    }


}