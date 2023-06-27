package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

import java.util.LinkedHashSet;
import java.util.Set;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
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
    void listOwners() throws Exception {
        when(this.ownerService.findAll()).thenReturn(this.ownerSet);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/owners")).andExpect(status().isOk()).andExpect(
                view().name("owners/index")).andExpect(model().attribute("owners", Matchers.hasSize(2)));
    }

    @Test
    void listOwnersByIndex() throws Exception {
        when(this.ownerService.findAll()).thenReturn(this.ownerSet);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/owners/index")).andExpect(status().isOk()).andExpect(
                view().name("owners/index")).andExpect(model().attribute("owners", Matchers.hasSize(2)));
    }

    @Test
    void findOwners() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/owners/find")).andExpect(status().isOk()).andExpect(view().name("notimplemented"));
        verifyNoInteractions(this.ownerService);
    }
}