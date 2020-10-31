package com.task.fleetcomplete.Dto.VehicleData;

public class Vehicles {

    private float latitude;
    private float longitude;
    private Integer speed;
    private Integer enginestate;
    private String lastEngineOnTime;
    private Integer objectId;
    private String address;
    private String plate;

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Integer getEnginestate() {
        return enginestate;
    }

    public void setEnginestate(Integer enginestate) {
        this.enginestate = enginestate;
    }

    public String getLastEngineOnTime() {
        return lastEngineOnTime;
    }

    public void setLastEngineOnTime(String lastEngineOnTime) {
        this.lastEngineOnTime = lastEngineOnTime;
    }

    public Integer getObjectId() {
        return objectId;
    }

    public void setObjectId(Integer objectId) {
        this.objectId = objectId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }
}
