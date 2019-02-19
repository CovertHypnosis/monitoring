package ge.jemali.monitoring.controllers;

import ge.jemali.monitoring.models.Measurement;
import ge.jemali.monitoring.models.User;
import ge.jemali.monitoring.services.MeasureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/monitoring")
public class MeasureController {
    @Autowired
    private MeasureService measureService;

    @GetMapping("/measurements/{userId}")
    public List<Measurement> getMeasurements(@PathVariable String userId) {
        return measureService.findMeasurements(userId);
    }

    @PostMapping("/measurements/{userId}")
    public String addMeasurement(@PathVariable String userId,@Valid @RequestBody Measurement measurements) {
        measureService.addMeasurement(measurements, userId);
        return "inserted";
    }

    @PostMapping("/users")
    public String addUser(@RequestBody User user) {
        measureService.addUser(user);
        return "inserted";
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return measureService.getAllUsers();
    }

}
