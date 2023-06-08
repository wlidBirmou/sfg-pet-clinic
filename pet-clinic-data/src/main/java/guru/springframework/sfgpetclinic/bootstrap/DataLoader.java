package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    @Autowired
    public DataLoader(OwnerService ownerService, VetService vetService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        System.out.println("Data loader initialized....");
    }

    @Override
    public void run(String... args) throws Exception {


        Owner owner1=new Owner();
        owner1.setId(1L);
        owner1.setFirstName("Mechael");
        owner1.setLastName("Weston");
        ownerService.save(owner1);

        Owner owner2=new Owner();
        owner2.setId(2L);
        owner2.setFirstName("Fiona");
        owner2.setLastName("Glennane");
        ownerService.save(owner2);

        System.out.println("Loaded owners....");
        Vet Vet1=new Vet();
        Vet1.setId(1L);
        Vet1.setFirstName("Sam");
        Vet1.setLastName("Axe");
        vetService.save(Vet1);

        Vet Vet2=new Vet();
        Vet2.setId(2L);
        Vet2.setFirstName("Jessie");
        Vet2.setLastName("Porter");
        vetService.save(Vet2);

        System.out.println("Loaded vets....");



    }
}
