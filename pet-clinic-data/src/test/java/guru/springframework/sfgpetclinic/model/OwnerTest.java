package guru.springframework.sfgpetclinic.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OwnerTest {

    @Test
    void testEquals() {
        Owner owner1=Owner.builder().id(4l).firstName("a").lastName("a").city("a").address("a").telephone("1").build();
        Owner owner2=Owner.builder().id(4l).firstName("b").lastName("b").city("b").address("b").telephone("2").build();
        Owner owner3=Owner.builder().id(5l).firstName("a").lastName("a").city("a").address("a").telephone("1").build();

        assertTrue(owner1.equals(owner2));
        assertFalse(owner1.equals(owner3));
    }

    @Test
    void testHashCode() {
    }
}