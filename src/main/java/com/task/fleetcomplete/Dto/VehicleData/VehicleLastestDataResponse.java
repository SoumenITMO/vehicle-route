package com.task.fleetcomplete.Dto.VehicleData;

import java.util.List;

public class VehicleLastestDataResponse {
    
    private List<Vehicles> response;

    public List<Vehicles> getResponse() {
        return response;
    }

    public void setResponse(List<Vehicles> response) {
        this.response = response;
    }
}
