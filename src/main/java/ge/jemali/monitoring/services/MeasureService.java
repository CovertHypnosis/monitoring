package ge.jemali.monitoring.services;

import ge.jemali.monitoring.models.Measurement;
import ge.jemali.monitoring.models.User;
import ge.jemali.monitoring.repositories.MeasurementRepository;
import ge.jemali.monitoring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class MeasureService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    MeasurementRepository measurementRepository;


    public List<Measurement> findMeasurements(Long userId) {
        return measurementRepository.findByUserId(userId);
    }

    @Transactional
    public void addMeasurement(Measurement measurements) {
        measurementRepository.save(measurements);
    }

    @Transactional
    public void addUser(User user) {
        userRepository.save(user);
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
