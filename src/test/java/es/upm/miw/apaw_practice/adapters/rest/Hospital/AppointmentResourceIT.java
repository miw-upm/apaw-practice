package es.upm.miw.apaw_practice.adapters.rest.Hospital;

import es.upm.miw.apaw_practice.domain.models.Hospital.Appointment;
import es.upm.miw.apaw_practice.domain.services.Hospital.AppointmentService;
import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.http.MediaType;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AppointmentResource.class)
@RestTestConfig
public class AppointmentResourceIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AppointmentService appointmentService;

    private List<Appointment> appointmentList;

    @BeforeEach
    void setUp() {
        // Initialize a list of mock appointments
        Appointment appointment1 = new Appointment("1", "John Doe", "2024-10-01", "Dentist");
        Appointment appointment2 = new Appointment("2", "Jane Doe", "2024-10-02", "Cardiologist");

        appointmentList = Arrays.asList(appointment1, appointment2);
    }

    @Test
    void testFindAll() throws Exception {
        // Mock the findAll method of AppointmentService
        when(appointmentService.findAll()).thenReturn(appointmentList);

        // Perform the GET request and validate the response
        mockMvc.perform(MockMvcRequestBuilders.get(AppointmentResource.APPOINTMENTS)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].patientName").value("John Doe"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value("2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].patientName").value("Jane Doe"));

        // Verify that the service was called exactly once
        verify(appointmentService, times(1)).findAll();
    }
}
