package ge.jemali.monitoring;

import com.fasterxml.jackson.databind.ObjectMapper;
import ge.jemali.monitoring.enums.MeasurementType;
import ge.jemali.monitoring.models.dto.MeasurementDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SimpleTest {
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void userTest() throws Exception {
        this.mvc.perform(get("/users")).
                andExpect(status().isOk()).
                andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).
                andExpect(content().string(containsString("jemali")));
    }

    @Test
    public void wrongEndpointTest() throws Exception {
        this.mvc.perform(get("/measurements/100")).
                andExpect(status().isNotFound());
    }

    @Test
    public void incorrectEndpointTest() throws Exception {
        this.mvc.perform(get("/measurements/-1")).
                andExpect(status().isBadRequest());
    }

    @Test
    public void addMeasurementTest() throws Exception {
        this.mvc.perform(post("/measurements/1").
                content(asJsonString(new MeasurementDTO(MeasurementType.COLD_WATER,"22222", 1L))).
                contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).
                accept(MediaType.APPLICATION_JSON_UTF8_VALUE)).
                andExpect(status().isOk());
    }

    public String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
