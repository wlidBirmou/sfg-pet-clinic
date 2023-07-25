package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.exceptions.NotFoundException;
import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.repositories.SpecialityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class SpecialitySDJpaServiceTest {
    @Mock
    private SpecialityRepository specialityRepository;
    @Mock
    private MessageSource messageSource;
    @InjectMocks
    private SpecialitySDJpaService specialitySDJpaService;
    private Speciality speciality;
    @BeforeEach
    void setUp() {
        this.speciality = Speciality.builder().id(1l).description("spe1").build();

    }

    @Test
    void testFindById() {
        when(this.specialityRepository.findById(1l)).thenReturn(Optional.of(this.speciality));
        when(this.messageSource.getMessage(anyString(), any(Object[].class) , any(Locale.class))).thenReturn("pet");

        Speciality returnedSpeciality=this.specialitySDJpaService.findById(1l);
        assertNotNull(returnedSpeciality);
        assertEquals(1l,returnedSpeciality.getId());
        verify(this.specialityRepository,times(1)).findById(anyLong());
        verify(this.messageSource,times(1)).getMessage(anyString(),any(Object[].class),any(Locale.class));
    }

    @Test
    void testFindByIdNotFound() {
        when(this.specialityRepository.findById(1l)).thenReturn(Optional.empty());
        when(this.messageSource.getMessage(anyString(), any(Object[].class) , any(Locale.class))).thenReturn("pet");

        assertThrows(NotFoundException.class,()-> this.specialitySDJpaService.findById(1l));
        verify(this.specialityRepository,times(1)).findById(anyLong());
        verify(this.messageSource,times(2)).getMessage(anyString(),any(Object[].class),any(Locale.class));
    }

    @Test
    void testSave() {
        when(this.specialityRepository.save(eq(speciality))).thenReturn(speciality);
        Speciality savedSpeciality=this.specialitySDJpaService.save(speciality);
        assertNotNull(savedSpeciality);
        assertEquals(savedSpeciality,speciality);
        verify(this.specialityRepository,times(1)).save(any());
    }

    @Test
    void testFindAll() {
        List<Speciality> list=new ArrayList<>();
        list.add(speciality);

        when(this.specialityRepository.findAll()).thenReturn(list);

        Set<Speciality> specialitys=this.specialitySDJpaService.findAll();
        assertNotNull(specialitys);
        assertEquals(1,specialitys.size());
        verify(this.specialityRepository,times(1)).findAll();
    }

    @Test
    void testDelete() {
        this.specialitySDJpaService.delete(speciality);
        verify(this.specialityRepository,times(1)).delete(any());
    }

    @Test
    void testDeleteById() {
        this.specialitySDJpaService.deleteById(1l);
        verify(this.specialityRepository,times(1)).deleteById(any());
    }
}