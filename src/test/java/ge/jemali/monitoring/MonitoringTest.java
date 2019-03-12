package ge.jemali.monitoring;

import ge.jemali.monitoring.enums.MeasurementType;
import ge.jemali.monitoring.dto.MeasurementDTO;
import ge.jemali.monitoring.services.MeasureService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MonitoringTest {
    @MockBean
    private MeasureService measureService;

    @Test
    public void testService() {
        Mockito.when(measureService.findMeasurementsByUserId(1L)).
                thenReturn(new ArrayList<>(Arrays.asList(new MeasurementDTO(MeasurementType.COLD_WATER,
                        "22222", 1L))));
        MeasurementDTO measurementDTO = new MeasurementDTO(MeasurementType.COLD_WATER, "22222", 1L);
        Assert.assertEquals(measurementDTO, measureService.findMeasurementsByUserId(1L).get(0));
    }
}
