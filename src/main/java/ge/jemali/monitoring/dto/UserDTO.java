package ge.jemali.monitoring.dto;

import ge.jemali.monitoring.models.Measurement;

import java.util.List;

public class UserDTO {
    private String name;
    private List<Measurement> measurements;

    public UserDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Measurement> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(List<Measurement> measurements) {
        this.measurements = measurements;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "name='" + name + '\'' +
                ", measurements=" + measurements +
                '}';
    }
}