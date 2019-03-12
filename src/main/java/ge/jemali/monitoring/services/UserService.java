package ge.jemali.monitoring.services;

import ge.jemali.monitoring.dto.UserDTO;
import ge.jemali.monitoring.models.User;
import ge.jemali.monitoring.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void addUser(UserDTO userDTO) {
        User user = convertToEntity(userDTO);
        userRepository.save(user);
    }

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(e -> convertToDto(e)).collect(Collectors.toList());
    }

    // utility methods
    private UserDTO convertToDto(User user) {
        return modelMapper.map(user, UserDTO.class);
    }
    private User convertToEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }
}
