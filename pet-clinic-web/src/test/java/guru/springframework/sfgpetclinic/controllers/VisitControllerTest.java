package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.VisitService;
import org.hamcrest.Matchers;
import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class VisitControllerTest {

    @InjectMocks
    private VisitController visitController;

    @Mock
    private PetService petService;
    @Mock
    private VisitService visitService;

    MockMvc mockMvc;
    Owner owner;
    Pet pet;
    Set<Visit> visits=new LinkedHashSet<>();
    @BeforeEach
    void setUp() {
        this.mockMvc= MockMvcBuilders.standaloneSetup(this.visitController).build();
        this.visits.add(Visit.builder().id(1l).description("a").build());
        this.visits.add(Visit.builder().id(2l).description("b").build());
        this.visits.add(Visit.builder().id(3l).description("c").build());
        this.pet=Pet.builder().id(1l).name("sisi").birthDate(LocalDate.now()).build();
        this.pet.setVisits(this.visits);
        this.visits.forEach(v-> v.setPet(this.pet));
        this.owner= Owner.builder().id(1l).lastName("last").firstName("first").build();
        this.owner.setPets(new LinkedHashSet<Pet>());
        this.owner.getPets().add(this.pet);
        this.pet.setOwner(owner);

        when(this.petService.findById(anyLong())).thenReturn(pet);
    }

    @Test
    public void testInitVisitCreationFormwithReferer() throws Exception {

        this.mockMvc.perform(get("/owners/1/pets/1/visits/new").header("referer","https/"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("visit"))
                .andExpect(view().name("pets/createOrUpdateVisitForm"));
        verify(this.petService,times(1)).findById(any());
        verifyNoInteractions(this.visitService);
    }

    @Test
    public void testInitVisitCreationFormWithoutReferer() throws Exception {

        this.mockMvc.perform(get("/owners/1/pets/1/visits/new"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("visit"))
                .andExpect(view().name("pets/createOrUpdateVisitForm"));
        verify(this.petService,times(1)).findById(any());
        verifyNoInteractions(this.visitService);
    }

    @Test
    public void testProcessVisitCreationForm() throws Exception {
        when(this.petService.save(any())).thenReturn(pet);
        this.mockMvc.perform(post("/owners/1/pets/1/visits/new"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"));

        verify(this.petService,times(1)).save(any());
        verifyNoInteractions(this.visitService);
    }
}