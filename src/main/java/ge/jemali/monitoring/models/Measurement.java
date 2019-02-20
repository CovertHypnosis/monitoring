package ge.jemali.monitoring.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Measurement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long measurementId;

    @Enumerated(EnumType.STRING)
    @Column(unique = false)
    private MeasurementType measurementType;

    @NotNull
    @Column(unique = false)
    private String record;

    @Column(unique = false)
    private long userId;

    public Measurement() {
    }

    public Measurement(long measurementId, MeasurementType measurementType, @NotNull String record, long userId) {
        this.measurementId = measurementId;
        this.measurementType = measurementType;
        this.record = record;
        this.userId = userId;
    }

    public long getMeasurementId() {
        return measurementId;
    }

    public void setMeasurementId(long measurementId) {
        this.measurementId = measurementId;
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

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
