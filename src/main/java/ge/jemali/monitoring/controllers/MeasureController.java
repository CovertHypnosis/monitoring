package ge.jemali.monitoring.controllers;

import ge.jemali.monitoring.dto.MeasurementDTO;
import ge.jemali.monitoring.services.MeasureService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/measurements")
@Api(value = "measurements", description = "measurements service", tags = ("measurements"))
public class MeasureController {
    private final MeasureService measureService;

    @Autowired
    public MeasureController(MeasureService measureService) {
        this.measureService = measureService;
    }

    @GetMapping("/{userId}")
    @ApiOperation(value = "get measurement by user id")
    public List<MeasurementDTO> getMeasurementsByUserId(@PathVariable Long userId) {
        return measureService.findMeasurementsByUserId(userId);
    }

    @PostMapping("/{userId}")
    @ApiOperation(value = "add measurement by user id")
    public String addMeasurement(@PathVariable Long userId,@Valid @RequestBody MeasurementDTO measurements) {
        measureService.addMeasurement(measurements, userId);
        return "inserted";
    }
}