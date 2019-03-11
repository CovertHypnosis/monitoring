package ge.jemali.monitoring.controllers;

import ge.jemali.monitoring.models.dto.UserDTO;
import ge.jemali.monitoring.services.MeasureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final MeasureService measureService;

    @Autowired
    public UserController(MeasureService measureService) {
        this.measureService = measureService;
    }

    @PostMapping
    public String addUser(@RequestBody UserDTO userDTO) {
        measureService.addUser(userDTO);
        return "inserted";
    }

    @GetMapping
    public List<UserDTO> getUsers() {
        return measureService.getAllUsers();
    }
}