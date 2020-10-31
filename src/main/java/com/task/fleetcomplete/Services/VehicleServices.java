package com.task.fleetcomplete.Services;

import com.task.fleetcomplete.Dto.APIError;
import com.task.fleetcomplete.Dto.VehicleData.VehicleProcessedData;
import com.task.fleetcomplete.Dto.VehicleData.VehicleRoute;
import com.task.fleetcomplete.Dto.VehicleData.Vehicles;
import com.task.fleetcomplete.Dto.VehicleProcessedData.VehicleRouteData;
import com.task.fleetcomplete.Dto.VehicleProcessedData.VehicleRoutePoints;
import com.task.fleetcomplete.Helper.HelperMethods;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class VehicleServices {

    private int difference = 0;
    private String lastUpdate = "";
    private final String pattern = "yyyy-MM-dd hh:mm:ss";
    private final Date now = new Date();
    private final SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
    private final Logger logger = LoggerFactory.getLogger(VehicleServices.class);

    @Autowired
    HelperMethods helperMethods;

    public ResponseEntity getVehicles(String apikey) {

        try {
            List<Vehicles> vehiclesPosition = helperMethods.getVehicles(apikey);
            List<VehicleProcessedData> processedVehiclesData = new ArrayList<>();
            vehiclesPosition.forEach(getVehicleData -> {
                try {
                    difference = (int)TimeUnit.HOURS.convert(now.getTime() -
                            dateFormat.parse(getVehicleData.getLastEngineOnTime()).getTime(), TimeUnit.MILLISECONDS);

                    if (difference < 24) {
                        lastUpdate = "Last updated " + difference + " hours ago.";
                    }
                    if (difference > 24) {
                        lastUpdate = getVehicleData.getLastEngineOnTime();
                    }
                    if (getVehicleData.getEnginestate() == 1) {
                        lastUpdate = "On the way";
                    }
                    System.out.println(lastUpdate);
                    processedVehiclesData.add(new VehicleProcessedData(getVehicleData.getPlate(),
                            getVehicleData.getSpeed() == null ? 0 : getVehicleData.getSpeed(), getVehicleData.getObjectId(),
                            getVehicleData.getAddress(), lastUpdate, getVehicleData.getLatitude(), getVehicleData.getLongitude()));
                } catch (ParseException parseException) {
                    logger.error(parseException.getMessage());
                }
            });
            return new ResponseEntity<>(processedVehiclesData, HttpStatus.OK);

        } catch (
                HttpClientErrorException httpException) {
            logger.error("Http Exception Happend.");
            return new ResponseEntity<>(new APIError(101), HttpStatus.OK);
        }
    }

    public ResponseEntity vehicleRoute(String apiKey, String objectId, String startDate, String endDate) {

        int stops = 0;
        int totalDistance = 0;
        int totalStopsDistance = 0;
        boolean stopFlag = false;
        int lastDistance = 0;

        try {
            List<VehicleRoute> vehicleRoutesData = helperMethods.getVehicleRoute(apiKey, objectId, startDate, endDate);
            List<VehicleRoutePoints> mapPoints = new ArrayList<>();

            if(vehicleRoutesData.size() == 0) {
                return new ResponseEntity<>(new APIError(403), HttpStatus.OK);
            }
            totalDistance = vehicleRoutesData.get(vehicleRoutesData.size() - 1).getDistance() -
                    vehicleRoutesData.get(0).getDistance();
            for (VehicleRoute vehicleRoutesDatum : vehicleRoutesData) {
                try {
                    if(vehicleRoutesDatum.getSpeed() == null && stopFlag) {
                        stops++;
                        stopFlag = false;
                        totalStopsDistance += vehicleRoutesDatum.getDistance() - lastDistance;
                    }
                    if(vehicleRoutesDatum.getSpeed() > 0) {
                        stopFlag = true;
                        lastDistance = vehicleRoutesDatum.getDistance();
                    }
                    mapPoints.add(new VehicleRoutePoints(vehicleRoutesDatum.getLatitude(), vehicleRoutesDatum.getLongitude()));
                } catch (NullPointerException npe) { }
            }
            return new ResponseEntity<>(new VehicleRouteData(totalDistance, stops, totalStopsDistance,
                    totalDistance - totalStopsDistance, mapPoints), HttpStatus.OK);
        } catch (HttpClientErrorException httpException) {
            logger.error("Http Exception Happend.");
            return new ResponseEntity<>(new APIError(101), HttpStatus.OK);
        }
    }
}
