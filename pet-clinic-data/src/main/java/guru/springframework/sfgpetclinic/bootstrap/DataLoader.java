package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    @Autowired
    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        System.out.println("Data loader initialized....");
    }

    @Override
    public void run(String... args) throws Exception {

        PetType dog = new PetType();
        dog.setName("Dog");
        this.petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        this.petTypeService.save(cat);
        System.out.println("petType Loaded");


        Owner owner1 = new Owner();
        owner1.setId(1L);
        owner1.setFirstName("Mechael");
        owner1.setLastName("Weston");
        owner1.setAddress("123 Brickerel");
        owner1.setCity("Miami");
        owner1.setTelephone("123493049");
        Pet mikesPet=new Pet();
        mikesPet.setPetType(dog);
        mikesPet.setOwner(owner1);
        mikesPet.setName("Rosco");
        mikesPet.setBirthDate(LocalDate.of(2011,02,05));
        owner1.getPets().add(mikesPet);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setId(2L);
        owner2.setFirstName("Fiona");
        owner2.setLastName("Glennane");
        owner2.setAddress("123 Brickerel");
        owner2.setCity("Miami");
        owner2.setTelephone("123493049");

        Pet fionaPet=new Pet();
        fionaPet.setPetType(cat);
        fionaPet.setOwner(owner2);
        fionaPet.setName("Sisi");
        fionaPet.setBirthDate(LocalDate.of(2016,03,12));
        owner2.getPets().add(fionaPet);

        ownerService.save(owner2);

        System.out.println("Loaded owners....");
        Vet Vet1 = new Vet();
        Vet1.setId(1L);
        Vet1.setFirstName("Sam");
        Vet1.setLastName("Axe");
        vetService.save(Vet1);

        Vet Vet2 = new Vet();
        Vet2.setId(2L);
        Vet2.setFirstName("Jessie");
        Vet2.setLastName("Porter");
        vetService.save(Vet2);

        System.out.println("Loaded vets....");

    }


}
