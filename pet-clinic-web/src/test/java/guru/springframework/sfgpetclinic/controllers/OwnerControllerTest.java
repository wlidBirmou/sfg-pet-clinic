package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    @Mock
    private OwnerService ownerService;
    @InjectMocks
    private OwnerController ownerController;
    private Set<Owner> ownerSet;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        this.ownerSet=new LinkedHashSet<>();
        Owner owner1=Owner.builder().id(1l).firstName("Abderrahim").lastName("LAAKAB").address("123 Sidi yahia")
                .city("Algiers").telephone("0014383720520").build();
        Owner owner2=Owner.builder().id(2l).firstName("Said").lastName("MOUHOUNE").address("123 WAHAT, Hydra")
                .city("Algiers").telephone("00213772847864").build();
        this.ownerSet.add(owner1);
        this.ownerSet.add(owner2);

        this.mockMvc= MockMvcBuilders.standaloneSetup(ownerController).build();
    }


    @Test
    void viewOwner() throws Exception{
        Owner owner=Owner.builder().id(1l).firstName("Abderrahim").lastName("LAAKAB").address("123 Sidi yahia").city("Algiers").telephone("0014383720520").build();

        when(this.ownerService.findById(anyLong())).thenReturn(owner);

        this.mockMvc.perform(get("/owners/1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("owner"))
                .andExpect(model().attribute("owner",hasProperty("id",is(1L))))
                .andExpect(view().name("owners/ownerDetails"));
    }

    @Test
    void findOwners() throws Exception{
        this.mockMvc.perform(get("/owners/find"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("owner"))
                .andExpect(model().attribute("owner",hasProperty("id",nullValue())))
                .andExpect(view().name("owners/findOwners"));

        verifyNoInteractions(this.ownerService);

    }

    @Test
    void ProcessFindFormReturnMany() throws Exception{

        List<Owner> owners=new ArrayList<>();
        owners.add(Owner.builder().id(1l).lastName("Laakab").firstName("Abderrahim").telephone("268466556").city("CDN").build());
        owners.add(Owner.builder().id(2l).lastName("Laakab").firstName("Mohamed").telephone("268466556").city("NDG").build());

        when(this.ownerService.findAllByLastNameContains(any())).thenReturn(owners);
        this.mockMvc.perform(get("/owners/search-owner-for"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("owners"))
                .andExpect(model().attribute("owners",hasSize(2)))
                .andExpect(view().name("owners/ownerList"));
        verify(this.ownerService,times(1)).findAllByLastNameContains(anyString());
    }

    @Test
    void ProcessFindFormReturnOne() throws Exception{

        List<Owner> owners=List.of(Owner.builder().id(1l).lastName("Laakab").firstName("Abderrahim").telephone("268466556").city("CDN").build());

        when(this.ownerService.findAllByLastNameContains(any())).thenReturn(owners);
        this.mockMvc.perform(get("/owners/search-owner-for"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"));
        verify(this.ownerService,times(1)).findAllByLastNameContains(anyString());
    }

    @Test
    void initCreationForm() throws Exception{


        mockMvc.perform(get("/owners/new"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("owner"))
                .andExpect(model().attribute("owner",hasProperty("id",IsNull.nullValue())))
                .andExpect(view().name("owners/createOrUpdateForm"));

        verifyNoInteractions(this.ownerService);
    }

    @Test
    void processCreationForm() throws Exception {
        when(ownerService.save(any())).thenReturn(Owner.builder().id(1l).build());

        mockMvc.perform((post("/owners/new")))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"));
        verify(ownerService, times(1)).save(any());
    }
    @Test
    void initUpdateForm() throws Exception{
        Owner owner=Owner.builder().id(1l).firstName("a").lastName("b").build();

        when(this.ownerService.findById(any())).thenReturn(owner);
        mockMvc.perform(get("/owners/1/edit"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("owner"))
                .andExpect(model().attribute("owner",hasProperty("id", IsNull.notNullValue())))
                .andExpect(view().name("owners/createOrUpdateForm"));
        verify(this.ownerService,times(1)).findById(any());
    }

    @Test
    void processUpdateForm() throws Exception {

        when(ownerService.save(any())).thenReturn(Owner.builder().id(1l).lastName("a").firstName("b").build());

        mockMvc.perform((post("/owners/1/edit")))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"));
        verify(ownerService).save(any());
    }

}