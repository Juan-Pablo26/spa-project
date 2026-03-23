package cl.spa.backend.controller;

import cl.spa.backend.model.Plan;
import cl.spa.backend.repository.PlanRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PlanController.class)
class PlanControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PlanRepository repository;

    @Test
    void GetAllPlanes() throws Exception {
        Plan plan1 = new Plan("Basico", "Plan simple", 10000.0);
        Plan plan2 = new Plan("Premium", "Plan completo", 20000.0);

        Mockito.when(repository.findAll()).thenReturn(List.of(plan1, plan2));

        mockMvc.perform(get("/api/planes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Basico"))
                .andExpect(jsonPath("$[0].descripcion").value("Plan simple"))
                .andExpect(jsonPath("$[0].precio").value(10000.0))
                .andExpect(jsonPath("$[1].nombre").value("Premium"));
    }
}
