package ge.jemali.monitoring.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Measurement {
    @Id
    private long measurementId;

    @NotNull
    private String gas;

    @NotNull
    private String coldWater;

    @NotNull
    private String hotWater;

    private long userId;

    public Measurement() {
    }

    public Measurement(long measurementId, @NotNull String gas, @NotNull String coldWater, @NotNull String hotWater,
                       long userId) {
        this.measurementId = measurementId;
        this.gas = gas;
        this.coldWater = coldWater;
        this.hotWater = hotWater;
        this.userId = userId;
    }



    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getGas() {
        return gas;
    }

    public void setGas(String gas) {
        this.gas = gas;
    }

    public String getColdWater() {
        return coldWater;
    }

    public void setColdWater(String coldWater) {
        this.coldWater = coldWater;
    }

    public String getHotWater() {
        return hotWater;
    }

    public void setHotWater(String hotWater) {
        this.hotWater = hotWater;
    }

    public long getMeasurementId() {
        return measurementId;
    }

    public void setMeasurementId(long measurementId) {
        this.measurementId = measurementId;
    }
}
