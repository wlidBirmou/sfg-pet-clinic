package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.*;
import guru.springframework.sfgpetclinic.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;
    private final VisitService visitService;

    @Autowired
    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialityService specialityService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
        this.visitService = visitService;
        System.out.println("Data loader initialized....");
    }

    @Override
    public void run(String... args) throws Exception {

       if(this.petTypeService.findAll().size()==0) loadData();

    }

    private void loadData() {
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

        System.out.println("Loaded Visits....");
        Visit catVisit=new Visit();
        catVisit.setPet(fionaPet);
        catVisit.setDescription("Sneezy kitty");
        catVisit.setDate(LocalDate.now());

        this.visitService.save(catVisit);

        System.out.println("Loaded owners....");


        Speciality radiology=new Speciality();
        radiology.setDescription("Radiology");
        this.specialityService.save(radiology);

        Speciality surgery=new Speciality();
        surgery.setDescription("Surgery");
        this.specialityService.save(surgery);

        Speciality dentistry=new Speciality();
        dentistry.setDescription("Dentistry");
        this.specialityService.save(dentistry);

        System.out.println("Loaded specialties....");

        Vet vet1 = new Vet();
        vet1.setId(1L);
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");
        vet1.getSpecialities().add(radiology);
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setId(2L);
        vet2.setFirstName("Jessie");
        vet2.setLastName("Porter");
        vet2.getSpecialities().add(surgery);
        vetService.save(vet2);

        System.out.println("Loaded vets....");
    }


}
