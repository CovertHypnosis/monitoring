package ge.jemali.monitoring.dto;

import ge.jemali.monitoring.enums.MeasurementType;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class MeasurementDTO {
    @NotNull
    private MeasurementType measurementType;
    @NotNull
    private String record;
    @NotNull
    private Long userId;

    public MeasurementDTO() {
    }

    public MeasurementDTO(@NotNull MeasurementType measurementType, @NotNull String record, @NotNull Long userId) {
        this.measurementType = measurementType;
        this.record = record;
        this.userId = userId;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "MeasurementDTO{" +
                "measurementType=" + measurementType +
                ", record='" + record + '\'' +
                ", user=" + userId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MeasurementDTO that = (MeasurementDTO) o;
        return measurementType == that.measurementType &&
                Objects.equals(record, that.record) &&
                Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(measurementType, record, userId);
    }
}