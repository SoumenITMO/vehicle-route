package com.task.fleetcomplete.Helper;

import com.task.fleetcomplete.Dto.VehicleData.VehicleLastestDataResponse;
import com.task.fleetcomplete.Dto.VehicleData.VehicleRoute;
import com.task.fleetcomplete.Dto.VehicleData.VehicleRouteResponse;
import com.task.fleetcomplete.Dto.VehicleData.Vehicles;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
public class HelperMethods {

    private RestTemplate restTemplate = new RestTemplateBuilder()
            .setConnectTimeout(Duration.ofMillis(70))
            .setReadTimeout(Duration.ofMillis(70))
            .build();

    public List<Vehicles> getVehicles(String apiKey) {

        String vehicleLastPositionURL = "https://app.ecofleet.com/seeme/Api/Vehicles/getLastData?key=".concat(apiKey).concat("&json");
        List<Vehicles> vehicleLastestDataResponse = restTemplate.getForObject(vehicleLastPositionURL,
                VehicleLastestDataResponse.class).getResponse();
        return vehicleLastestDataResponse;
    }

    public List<VehicleRoute> getVehicleRoute(String apiKey, String objectId, String startDate, String endDate) {

        String vehicleRouteURL = "https://app.ecofleet.com/seeme/Api/Vehicles/getRawData?objectId=".concat(objectId)+"&begTimestamp="
                .concat(startDate)+"&endTimestamp=".concat(endDate)+"&key=".concat(apiKey).concat("&json");

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        try {
            HttpEntity<String> entity = new HttpEntity<>(headers);
            ResponseEntity<VehicleRouteResponse> response = restTemplate.exchange(vehicleRouteURL, HttpMethod.GET,
                    entity, VehicleRouteResponse.class);
            return response.getBody().getResponse();
        } catch (HttpServerErrorException httpServerErrorException) {
        }
        return new ArrayList<>();
    }
}
