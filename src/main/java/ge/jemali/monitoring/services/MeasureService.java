package ge.jemali.monitoring.services;

import ge.jemali.monitoring.exceptions.RecordNotFoundException;
import ge.jemali.monitoring.exceptions.RecordSyntaxException;
import ge.jemali.monitoring.models.Measurement;
import ge.jemali.monitoring.models.User;
import ge.jemali.monitoring.repositories.MeasurementRepository;
import ge.jemali.monitoring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static org.springframework.util.CollectionUtils.isEmpty;

@Service
public class MeasureService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MeasurementRepository measurementRepository;

    public List<Measurement> findMeasurements(String userId) {
        if (!userId.matches(".*\\d+.*") || Long.parseLong(userId) < 0)
            throw new RecordSyntaxException("wrong syntax");

        // if it's not wrong typed id, make new variable for user id
        long id = Long.parseLong(userId);

        if (isEmpty(measurementRepository.findByUserId(id)))
            throw new RecordNotFoundException("no such user in database");

        return measurementRepository.findByUserId(id);
    }

    @Transactional
    public void addMeasurement(Measurement measurements, String userId) {
        if (!userId.matches(".*\\d+.*") || Long.parseLong(userId) < 0)
            throw new RecordSyntaxException("wrong syntax");

        long id = Long.parseLong(userId);

        measurements.setUserId(id);
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
