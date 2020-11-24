package com.task.fleetcomplete.Dto.VehicleData;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;

public class VehicleRoute {

    @NotNull
    @JsonProperty("Speed")
    private Integer speed;
    @NotNull
    @JsonProperty("Distance")
    private Integer distance;
    @NotNull
    @JsonProperty("EngineStatus")
    private Integer engineStatus;
    @NotNull
    @JsonProperty("Longitude")
    private Float longitude;
    @NotNull
    @JsonProperty("Latitude")
    private Float latitude;

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Integer getEngineStatus() {
        return engineStatus;
    }

    public void setEngineStatus(Integer engineStatus) {
        this.engineStatus = engineStatus;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }
}
