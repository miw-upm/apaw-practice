package es.upm.miw.apaw_practice.adapters.rest.cinema;

import es.upm.miw.apaw_practice.ApawPracticeApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = ApawPracticeApplication.class)
@AutoConfigureMockMvc
class MovieResourceIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testUpdateMovie_put() throws Exception {
        // Suponiendo que "Titanic" ya existe
        String updateJson = """
            {
                "title": "Titanic",
                "releaseDate": "1997-12-19",
                "description": "Película de amor y tragedia actualizada"
            }
            """;
        this.mockMvc.perform(put("/cinema/movies/Titanic")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value("Película de amor y tragedia actualizada"));
    }

    @Test
    void testDeleteMovie_delete() throws Exception {
        // Suponiendo que "Titanic" ya existe
        this.mockMvc.perform(delete("/cinema/movies/Titanic"))
                .andExpect(status().isNoContent());
        // Puedes verificar que ya no está:
        this.mockMvc.perform(get("/cinema/movies/Titanic"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testPatchMovie_patch() throws Exception {
        // Suponiendo que "Titanic" ya existe
        String patchJson = """
            {
                "description": "Parche sólo en descripción"
            }
            """;
        this.mockMvc.perform(patch("/cinema/movies/Titanic")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(patchJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value("Parche sólo en descripción"));
    }
}