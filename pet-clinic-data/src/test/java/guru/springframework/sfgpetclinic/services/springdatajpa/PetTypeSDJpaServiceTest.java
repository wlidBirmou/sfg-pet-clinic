package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.exceptions.NotFoundException;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.repositories.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class PetTypeTypeSDJpaServiceTest {

    @Mock
    private PetTypeRepository petTypeRepository;
    @Mock
    private MessageSource messageSource;
    @InjectMocks
    private PetTypeSDJpaService petTypeSDJpaService;
    private PetType petType;
    @BeforeEach
    void setUp() {
        this.petType = PetType.builder().id(1l).name("cat").build();

    }

    @Test
    void testFindById() {
        when(this.petTypeRepository.findById(1l)).thenReturn(Optional.of(this.petType));
        when(this.messageSource.getMessage(anyString(), any(Object[].class) , any(Locale.class))).thenReturn("pet");

        PetType returnedPetType=this.petTypeSDJpaService.findById(1l);
        assertNotNull(returnedPetType);
        assertEquals(1l,returnedPetType.getId());
        verify(this.petTypeRepository,times(1)).findById(anyLong());
        verify(this.messageSource,times(1)).getMessage(anyString(),any(Object[].class),any(Locale.class));
    }

    @Test
    void testFindByIdNotFound() {
        when(this.petTypeRepository.findById(1l)).thenReturn(Optional.empty());
        when(this.messageSource.getMessage(anyString(), any(Object[].class) , any(Locale.class))).thenReturn("pet");

        assertThrows(NotFoundException.class,()-> this.petTypeSDJpaService.findById(1l));
        verify(this.petTypeRepository,times(1)).findById(anyLong());
        verify(this.messageSource,times(2)).getMessage(anyString(),any(Object[].class),any(Locale.class));
    }

    @Test
    void testSave() {
        when(this.petTypeRepository.save(eq(petType))).thenReturn(petType);
        PetType savedPetType=this.petTypeSDJpaService.save(petType);
        assertNotNull(savedPetType);
        assertEquals(savedPetType,petType);
        verify(this.petTypeRepository,times(1)).save(any());
    }

    @Test
    void testFindAll() {
        List<PetType> list=new ArrayList<>();
        list.add(petType);

        when(this.petTypeRepository.findAll()).thenReturn(list);

        Set<PetType> petTypes=this.petTypeSDJpaService.findAll();
        assertNotNull(petTypes);
        assertEquals(1,petTypes.size());
        verify(this.petTypeRepository,times(1)).findAll();
    }

    @Test
    void testDelete() {
        this.petTypeSDJpaService.delete(petType);
        verify(this.petTypeRepository,times(1)).delete(any());
    }

    @Test
    void testDeleteById() {
        this.petTypeSDJpaService.deleteById(1l);
        verify(this.petTypeRepository,times(1)).deleteById(any());
    }
}