package es.upm.miw.apaw_practice.adapters.rest.Hospital;

import es.upm.miw.apaw_practice.domain.models.Hospital.Hospital;
import es.upm.miw.apaw_practice.domain.services.Hospital.HospitalService;
import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(HospitalResource.class)
@RestTestConfig
public class HospitalResourceIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HospitalService hospitalService;

    private Hospital hospital;

    @BeforeEach
    void setUp() {
        // Initialize a mock hospital object
        hospital = new Hospital("Hospital General", "Madrid", 500);
    }

    @Test
    void testCreateHospital() throws Exception {
        // Mock the create method of HospitalService
        when(hospitalService.create(hospital)).thenReturn(hospital);

        // Use ObjectMapper to convert the Hospital object to JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String hospitalJson = objectMapper.writeValueAsString(hospital);

        // Perform the POST request and validate the response
        mockMvc.perform(MockMvcRequestBuilders.post(HospitalResource.HOSPITALS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(hospitalJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Hospital General"))
                .andExpect(jsonPath("$.location").value("Madrid"))
                .andExpect(jsonPath("$.capacity").value(500));

        // Verify that the service layer's create method was called exactly once
        verify(hospitalService, times(1)).create(hospital);
    }
    @Test
    public void testCreateHospital() {
        // Creating mock lists of doctors and patients
        List<Doctor> doctors = new ArrayList<>();
        doctors.add(new Doctor("DNI789", "Cardiologist", new BigDecimal("50000")));

        List<Patient> patients = new ArrayList<>();
        patients.add(new Patient("DNI123", "John Doe", LocalDate.parse("1990-01-01"), true, new ArrayList<>()));

        // Matching the constructor parameters for Hospital
        Hospital hospital = new Hospital("Hospital General", "Madrid", "12345", 500, doctors, patients);

        // Assert and other test logic...
    }
}
