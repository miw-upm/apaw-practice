package es.upm.miw.apaw_practice.domain.models.veterinary_clinic;

import es.upm.miw.apaw_practice.domain.models.veterinay_clinic.OwnerClinic;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OwnerClinicTest {

    @Test
    void testBuilderFull() {
        OwnerClinic ownerClinic = OwnerClinic.builder()
                .name("Marcos")
                .address("Street Toledo")
                .phone("980453215")
                .build();
        assertEquals("Marcos", ownerClinic.getName());
        assertEquals("Street Toledo", ownerClinic.getAddress());
        assertEquals("980453215", ownerClinic.getPhone());
    }

    @Test
    void testBuilderPartial() {
        OwnerClinic ownerClinic = OwnerClinic.builder()
                .name("Aitana")
                .address("Street San Juan")
                .build();
        assertEquals("Aitana", ownerClinic.getName());
        assertEquals("Street San Juan", ownerClinic.getAddress());
    }
}
