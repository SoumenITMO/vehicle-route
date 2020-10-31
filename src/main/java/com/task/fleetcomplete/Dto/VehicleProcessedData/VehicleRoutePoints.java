package com.task.fleetcomplete.Dto.VehicleProcessedData;

public class VehicleRoutePoints {

    private float lat;
    private float lng;

    public VehicleRoutePoints(float lat, float lng) {
        this.lat = lat;
        this.lng = lng;
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
