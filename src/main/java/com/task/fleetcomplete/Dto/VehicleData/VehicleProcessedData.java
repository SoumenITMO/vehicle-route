package com.task.fleetcomplete.Dto.VehicleData;

public class VehicleProcessedData {

    private String plate;
    private Integer speed;
    private Integer objectId;
    private String address;
    private String lastUpdate;
    private float lat;
    private float lng;

    public VehicleProcessedData(String plate, Integer speed, Integer objectId, String address, String lastUpdate,
                                float lat, float lng) {
        this.plate = plate;
        this.speed = speed;
        this.objectId = objectId;
        this.address = address;
        this.lastUpdate = lastUpdate;
        this.lat = lat;
        this.lng = lng;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
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

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLng() {
        return lng;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }
}
