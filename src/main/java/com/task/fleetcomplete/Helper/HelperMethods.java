package com.task.fleetcomplete.Helper;

import com.task.fleetcomplete.Dto.VehicleData.VehicleLastestDataResponse;
import com.task.fleetcomplete.Dto.VehicleData.VehicleRoute;
import com.task.fleetcomplete.Dto.VehicleData.VehicleRouteResponse;
import com.task.fleetcomplete.Dto.VehicleData.Vehicles;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@Service
public class HelperMethods {

    private final RestTemplate restTemplate = new RestTemplate();

    public List<Vehicles> getVehicles(String apiKey) {

        String vehicleLastPositionURL = "https://app.ecofleet.com/seeme/Api/Vehicles/getLastData?key=".concat(apiKey).concat("&json");
        return restTemplate.getForObject(vehicleLastPositionURL, VehicleLastestDataResponse.class).getResponse();
    }

    public List<VehicleRoute> getVehicleRoute(String apiKey, String objectId, String startDate, String endDate) {

        String vehicleRouteURL = "https://app.ecofleet.com/seeme/Api/Vehicles/getRawData?objectId=".concat(objectId)+"&begTimestamp="
                .concat(startDate)+"&endTimestamp=".concat(endDate)+"&key=".concat(apiKey)+"&json";
        return restTemplate.getForObject(vehicleRouteURL, VehicleRouteResponse.class).getResponse();
    }
}
