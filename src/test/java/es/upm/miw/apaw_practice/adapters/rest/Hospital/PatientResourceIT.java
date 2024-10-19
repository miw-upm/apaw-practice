package es.upm.miw.apaw_practice.adapters.rest.Hospital;

import es.upm.miw.apaw_practice.domain.models.Hospital.Patient;
import es.upm.miw.apaw_practice.domain.services.Hospital.PatientService;
import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PatientResource.class)
@RestTestConfig
public class PatientResourceIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatientService patientService;

    private Patient patient;

    @BeforeEach
    void setUp() {
        // Initialize a mock patient object
        patient = new Patient("12345678A", "John Doe", "123-456-789", 30);
    }

    @Test
    void testDeletePatient() throws Exception {
        String dni = "12345678A";

        // Perform the DELETE request and validate the response
        mockMvc.perform(MockMvcRequestBuilders.delete(PatientResource.PATIENTS + "/{dni}", dni))
                .andExpect(status().isOk());

        // Verify that the service layer's delete method was called exactly once
        verify(patientService, times(1)).delete(dni);
    }

    @Test
    void testUpdatePatientName() throws Exception {
        String dni = "12345678A";
        String updatedName = "Jane Doe";

        // Mock the updateName method of PatientService
        when(patientService.updateName(dni, updatedName)).thenReturn(new Patient(dni, updatedName, "123-456-789", 30));

        // Perform the PATCH request and validate the response
        mockMvc.perform(MockMvcRequestBuilders.patch(PatientResource.PATIENTS + "/{dni}", dni)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedName))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.dni").value(dni))
                .andExpect(jsonPath("$.name").value(updatedName))
                .andExpect(jsonPath("$.phoneNumber").value("123-456-789"))
                .andExpect(jsonPath("$.age").value(30));

        // Verify that the service layer's updateName method was called exactly once
        verify(patientService, times(1)).updateName(dni, updatedName);
    }
    @Test
    public void testCreatePatient() {
        // Correcting the date type to LocalDate and matching constructor
        LocalDate birthDate = LocalDate.parse("1990-01-01");
        List<Appointment> appointments = new ArrayList<>();

        Patient patient = new Patient("DNI123", "John Doe", birthDate, true, appointments);

        // Assert and other test logic...
    }

    @Test
    public void testUpdatePatient() {
        LocalDate updatedBirthDate = LocalDate.parse("1985-05-10");
        List<Appointment> appointments = new ArrayList<>();

        // Updating the patient details
        Patient updatedPatient = new Patient("DNI456", "Jane Doe", updatedBirthDate, false, appointments);

        // Assert and other test logic...
    }
}
