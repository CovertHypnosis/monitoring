package ge.jemali.monitoring.controllers;

import ge.jemali.monitoring.dto.UserDTO;
import ge.jemali.monitoring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public String addUser(@RequestBody UserDTO userDTO) {
        userService.addUser(userDTO);
        return "inserted";
    }

    @GetMapping
    public List<UserDTO> getUsers() {
        return userService.getAllUsers();
    }
}