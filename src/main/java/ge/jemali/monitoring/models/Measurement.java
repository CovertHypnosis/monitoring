package ge.jemali.monitoring.models;

import ge.jemali.monitoring.enums.MeasurementType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Measurement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "measurement_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private MeasurementType measurementType;

    @NotNull
    private String record;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    public Measurement() {
    }

    public Measurement(MeasurementType measurementType, @NotNull String record, User user) {
        this.measurementType = measurementType;
        this.record = record;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MeasurementType getMeasurementType() {
        return measurementType;
    }

    public void setMeasurementType(MeasurementType measurementType) {
        this.measurementType = measurementType;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Measurement{" +
                "measurementType=" + measurementType +
                ", record='" + record + '\'' +
                ", user=" + user +
                '}';
    }
}