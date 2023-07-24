package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import org.hamcrest.Matchers;
import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(MockitoExtension.class)
class PetControllerTest {

    @InjectMocks
    private PetController petController;
    @Mock
    private PetService petService;
    @Mock
    private PetTypeService petTypeService;
    @Mock
    private OwnerService ownerService;

    private MockMvc mockMvc;
    private Owner owner;


    @BeforeEach
    void setUp() {

        this.mockMvc= MockMvcBuilders.standaloneSetup(petController).build();
        this.owner= Owner.builder().id(1l).firstName("a").lastName("b").build();
        PetType petType1=PetType.builder().id(1l).name("type1").build();
        PetType petType2=PetType.builder().id(2l).name("type2").build();
        PetType petType3=PetType.builder().id(3l).name("type3").build();

        Set<PetType> petTypes =new LinkedHashSet<>();
        petTypes.add(petType1);
        petTypes.add(petType2);
        petTypes.add(petType3);


        Pet pet1=Pet.builder().id(1l).name("pet1").build();
        Pet pet2=Pet.builder().id(2l).name("pet2").build();
        pet1.setPetType(petType1);
        pet2.setPetType(petType2);

        this.owner.getPets().add(pet1);
        this.owner.getPets().add(pet2);

        when(this.ownerService.findById(any())).thenReturn(owner);
        when(this.petTypeService.findAll()).thenReturn(petTypes);

    }


    @Test
    public void testInitCreationFormWithReferer() throws Exception{

        this.mockMvc.perform(get("/owners/1/pets/new").header("referer","https/"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("types"))
                .andExpect(model().attribute("types",hasSize(3)))
                .andExpect(model().attributeExists("owner"))
                .andExpect(model().attributeExists("pet"))
                .andExpect(model().attributeExists("referer"))
                .andExpect(model().attribute("pet", Matchers.hasProperty("id", IsNull.nullValue())))
                .andExpect(view().name("pets/createOrUpdatePetForm"));
    }
    @Test
    public void testInitCreationFormWithoutReferer() throws Exception{

        this.mockMvc.perform(get("/owners/1/pets/new"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("types"))
                .andExpect(model().attribute("types",hasSize(3)))
                .andExpect(model().attributeExists("owner"))
                .andExpect(model().attributeExists("pet"))
                .andExpect(model().attributeExists("referer"))
                .andExpect(model().attribute("pet", Matchers.hasProperty("id", IsNull.nullValue())))
                .andExpect(view().name("pets/createOrUpdatePetForm"));
    }

    @Test
    public void testProcessCreationForm() throws Exception{
        Pet pet=this.owner.getPets().iterator().next();
        when(this.ownerService.save(any())).thenReturn(owner);

        this.mockMvc.perform(post("/owners/1/pets/new"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"));
    }

    @Test
    public void testInitUpdateForm() throws Exception{

        when(this.petService.findById(anyLong())).thenReturn(this.owner.getPets().iterator().next());

        this.mockMvc.perform(get("/owners/1/pets/1/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("pets/createOrUpdatePetForm"));
    }

    @Test
    public void testProcessUpdateForm() throws Exception{
        Pet pet=this.owner.getPets().iterator().next();
        when(this.ownerService.save(any())).thenReturn(owner);

        this.mockMvc.perform(post("/owners/1/pets/1/edit"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"));
    }

}