package ge.jemali.monitoring.controllers;

import ge.jemali.monitoring.exceptions.RecordNotFoundException;
import ge.jemali.monitoring.exceptions.RecordSyntaxException;
import ge.jemali.monitoring.models.Measurement;
import ge.jemali.monitoring.models.User;
import ge.jemali.monitoring.repositories.MeasurementRepository;
import ge.jemali.monitoring.repositories.UserRepository;
import ge.jemali.monitoring.services.MeasureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

import static org.springframework.util.CollectionUtils.isEmpty;

@RestController
@RequestMapping("/monitoring")
public class MeasureController {
    @Autowired
    MeasureService measureService;

    @GetMapping("/measurements/{userId}")
    public List<Measurement> getMeasurements(@PathVariable String userId) {
        if (!userId.matches(".*\\d+.*") || Long.parseLong(userId) < 0)
            throw new RecordSyntaxException("wrong syntax");

        // if it's not wrong typed id, make new variable for user id
        long id = Long.parseLong(userId);

        if (isEmpty(measureService.findMeasurements(id)))
            throw new RecordNotFoundException("no such user in database");
        return measureService.findMeasurements(id);
    }

    @PostMapping("/measurements/{userId}")
    public String addMeasurement(@PathVariable Long userId,@Valid @RequestBody Measurement measurements) {
        measurements.setUserId(userId);
        measureService.addMeasurement(measurements);
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
