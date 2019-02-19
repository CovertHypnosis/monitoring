package ge.jemali.monitoring;

import ge.jemali.monitoring.controllers.MeasureController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MonitoringTest {
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MeasureController measureController;


    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }


    @Test
    public void testUser() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/monitoring/users")
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(content().string("[{\"userId\":0,\"name\":\"jemali\"}]"));


    }


    @Test
    public void testMeasurement() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/monitoring/measurements/0")
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().string("[{\"measurementId\":0,\"gas\":\"129129129\",\"coldWater\":\"123123123\"," +
                        "\"hotWater\":\"123123123\",\"userId\":0}]"));
    }
}
