package com.task.fleetcomplete.Dto.VehicleProcessedData;

import java.util.List;

public class VehicleRouteData {

    private float totalDistance;
    private int stops;
    private float stopsDistance;
    private float shortestDistance;
    private List<VehicleRoutePoints> vechicalroutepoints;

    public VehicleRouteData(float totalDistance, int stops, float stopsDistance, float shortestDistance, List<VehicleRoutePoints> vechicalroutepoints) {
        this.totalDistance = totalDistance;
        this.stops = stops;
        this.stopsDistance = stopsDistance;
        this.shortestDistance = shortestDistance;
        this.vechicalroutepoints = vechicalroutepoints;
    }

    public float getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(int totalDistance) {
        this.totalDistance = totalDistance;
    }

    public int getStops() {
        return stops;
    }

    public void setStops(int stops) {
        this.stops = stops;
    }

    public float getStopsDistance() {
        return stopsDistance;
    }

    public void setStopsDistance(int stopsDistance) {
        this.stopsDistance = stopsDistance;
    }

    public float getShortestDistance() {
        return shortestDistance;
    }

    public void setShortestDistance(int shortestDistance) {
        this.shortestDistance = shortestDistance;
    }

    public List<VehicleRoutePoints> getVechicalroutepoints() {
        return vechicalroutepoints;
    }

    public void setVechicalroutepoints(List<VehicleRoutePoints> vechicalroutepoints) {
        this.vechicalroutepoints = vechicalroutepoints;
    }
}
