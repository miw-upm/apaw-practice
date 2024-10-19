package es.upm.miw.apaw_practice.adapters.rest.Hospital;

import es.upm.miw.apaw_practice.domain.models.Hospital.Doctor;
import es.upm.miw.apaw_practice.domain.persistence_ports.Hospital.DoctorPersistence;
import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.math.BigDecimal;


@WebMvcTest(DoctorResource.class)
@RestTestConfig
public class DoctorResourceIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DoctorPersistence doctorPersistence;

    private Doctor updatedDoctor;

    @BeforeEach
    void setUp() {
        // Initialize the mock Doctor object
        updatedDoctor = new Doctor("12345678A", "Dr. Jane Doe", "Cardiology");
    }

    @Test
    void testUpdateDoctor() throws Exception {
        String dni = "12345678A";
        // Mock the update method of DoctorPersistence
        when(doctorPersistence.update(dni, updatedDoctor)).thenReturn(updatedDoctor);

        // Use ObjectMapper to convert the Doctor object to JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String updatedDoctorJson = objectMapper.writeValueAsString(updatedDoctor);

        // Perform the PUT request and validate the response
        mockMvc.perform(MockMvcRequestBuilders.put("/doctors/{dni}", dni)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedDoctorJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.dni").value("12345678A"))
                .andExpect(jsonPath("$.name").value("Dr. Jane Doe"))
                .andExpect(jsonPath("$.specialty").value("Cardiology"));

        // Verify that the persistence layer's update method was called exactly once
        verify(doctorPersistence, times(1)).update(dni, updatedDoctor);
    }
    @Test
    public void testCreateDoctor() {
        // Correcting the salary type to BigDecimal
        BigDecimal salary = new BigDecimal("50000");
        Doctor doctor = new Doctor("DNI789", "Cardiologist", salary);

        // Assert and other test logic...
    }
}
