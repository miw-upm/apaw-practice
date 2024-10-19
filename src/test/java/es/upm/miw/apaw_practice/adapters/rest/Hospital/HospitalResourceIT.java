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
        when(hospitalService.create(any(Hospital.class))).thenReturn(hospital);

        ObjectMapper objectMapper = new ObjectMapper();
        String hospitalJson = objectMapper.writeValueAsString(hospital);

        mockMvc.perform(MockMvcRequestBuilders.post(HospitalResource.HOSPITALS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(hospitalJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Hospital General"))
                .andExpect(jsonPath("$.location").value("Madrid"))
                .andExpect(jsonPath("$.capacity").value(500));

        verify(hospitalService, times(1)).create(any(Hospital.class));
    }


}
