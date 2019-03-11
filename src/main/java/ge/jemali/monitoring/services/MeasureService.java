package ge.jemali.monitoring.services;

import ge.jemali.monitoring.enums.MeasurementType;
import ge.jemali.monitoring.exceptions.RecordNotFoundException;
import ge.jemali.monitoring.exceptions.RecordSyntaxException;
import ge.jemali.monitoring.models.Measurement;
import ge.jemali.monitoring.models.User;
import ge.jemali.monitoring.models.dto.MeasurementDTO;
import ge.jemali.monitoring.models.dto.UserDTO;
import ge.jemali.monitoring.repositories.MeasurementRepository;
import ge.jemali.monitoring.repositories.UserRepository;
import org.apache.commons.lang3.EnumUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MeasureService {
    private final UserRepository userRepository;
    private final MeasurementRepository measurementRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public MeasureService(UserRepository userRepository, MeasurementRepository measurementRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.measurementRepository = measurementRepository;
        this.modelMapper = modelMapper;
    }

    public List<MeasurementDTO> findMeasurementsByUserId(Long userId) {
        validate(userId);
        List<Measurement> measurements = measurementRepository.findByUserId(userId);
        userRepository.findById(userId).
                orElseThrow(() -> new RecordNotFoundException("User id not found, check again"));
        return measurements.
                stream().
                map(e -> convertToDto(e)).
                collect(Collectors.toList());
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void addMeasurement(MeasurementDTO measurementDTO, Long userId) {
        if (!EnumUtils.isValidEnum(MeasurementType.class, measurementDTO.getMeasurementType().name())) {
            throw new RecordSyntaxException("enum is not valid, check again or contact administrator");
        }
        validate(userId);

        Measurement measurement = convertToEntity(measurementDTO);
        User user = userRepository.findById(userId).
                orElseThrow(() -> new RecordNotFoundException("User id not found, check again"));
        measurement.setUser(user);
        measurementRepository.save(measurement);
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

    // maybe add more logic later
    private void validate(Long userId) {
        if (userId < 0) {
            throw new RecordSyntaxException("Wrong syntax, please use valid numbers");
        }
    }

    // utility methods
    private UserDTO convertToDto(User user) {
        return modelMapper.map(user, UserDTO.class);
    }
    private User convertToEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }
    private MeasurementDTO convertToDto (Measurement measurement) {
        return modelMapper.map(measurement, MeasurementDTO.class);
    }
    private Measurement convertToEntity (MeasurementDTO measurementDto) {
        return modelMapper.map(measurementDto, Measurement.class);
    }
}