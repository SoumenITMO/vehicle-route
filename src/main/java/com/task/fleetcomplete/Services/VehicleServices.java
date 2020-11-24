package com.task.fleetcomplete.Services;

import com.task.fleetcomplete.Dto.VehicleData.VehicleProcessedData;
import com.task.fleetcomplete.Dto.VehicleData.VehicleRoute;
import com.task.fleetcomplete.Dto.VehicleData.Vehicles;
import com.task.fleetcomplete.Dto.VehicleProcessedData.VehicleRouteData;
import com.task.fleetcomplete.Dto.VehicleProcessedData.VehicleRoutePoints;
import com.task.fleetcomplete.Helper.HelperMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class VehicleServices {

    private String lastUpdate = "";
    private int hoursDifference = 0;
    private int daysDifference = 0;
    private final Date now = new Date();
    private final String pattern = "yyyy-MM-dd hh:mm:ss";
    private final SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);

    @Autowired
    HelperMethods helperMethods;

    public ResponseEntity getVehicles(String apikey) {

        List<Vehicles> vehiclesPosition = helperMethods.getVehicles(apikey);
        List<VehicleProcessedData> processedVehiclesData = new ArrayList<>();
        vehiclesPosition.forEach(getVehicleData -> {
            try {
                hoursDifference = (int)TimeUnit.HOURS.convert(now.getTime() -
                        dateFormat.parse(getVehicleData.getLastEngineOnTime()).getTime(), TimeUnit.MILLISECONDS);
                daysDifference = (int)TimeUnit.DAYS.convert(now.getTime() -
                        dateFormat.parse(getVehicleData.getLastEngineOnTime()).getTime(), TimeUnit.MILLISECONDS);

            } catch (ParseException parseException) {
            }

            if (getVehicleData.getEnginestate() == 1) {
                    lastUpdate = "On the way";
            }
            else if(daysDifference > 1 && daysDifference <= 6) {
                lastUpdate = "Last updated "+daysDifference+" days ago.";
            }
            else if(daysDifference == 1) {
                lastUpdate = "Last updated "+daysDifference+" day ago.";
            }
            else if(hoursDifference < 24) {
                lastUpdate = hoursDifference + " hours ago.";
            }
            else {
                lastUpdate = getVehicleData.getLastEngineOnTime();
            }
            processedVehiclesData.add(new VehicleProcessedData(getVehicleData.getPlate(),
                    getVehicleData.getSpeed() == null ? 0 : getVehicleData.getSpeed(), getVehicleData.getObjectId(),
                    getVehicleData.getAddress(), lastUpdate, getVehicleData.getLatitude(), getVehicleData.getLongitude()));
        });
        return new ResponseEntity<>(processedVehiclesData, HttpStatus.OK);
    }

    public ResponseEntity<VehicleRouteData> vehicleRoute(String apiKey, String objectId, String startDate, String endDate) {

        int stops = 0;
        int totalDistance = 0;
        int shortDistance = 0;
        int lastDistance = 0;
        boolean stopFlag = false;

        List<VehicleRoutePoints> mapPoints = new ArrayList<>();
        List<VehicleRoute> vehicleRoutesData = helperMethods.getVehicleRoute(apiKey, objectId, startDate, endDate);

        if(!vehicleRoutesData.isEmpty())
        {
            totalDistance = vehicleRoutesData.get(vehicleRoutesData.size() - 1).getDistance() -
                    vehicleRoutesData.get(0).getDistance();
            for (VehicleRoute getVehicleRouteData : vehicleRoutesData) {
                try {
                    if(getVehicleRouteData.getSpeed() == null && stopFlag) {
                        shortDistance += getVehicleRouteData.getDistance() - lastDistance;
                        stopFlag = false;
                        stops++;
                    }
                    if(getVehicleRouteData.getSpeed() > 0) {
                        lastDistance = getVehicleRouteData.getDistance();
                        stopFlag = true;
                    }
                    mapPoints.add(new VehicleRoutePoints(getVehicleRouteData.getLatitude(), getVehicleRouteData.getLongitude()));
                } catch (NullPointerException npe) { }
            }
            return new ResponseEntity<>(new VehicleRouteData(totalDistance, stops, shortDistance,
                    totalDistance - shortDistance, mapPoints), HttpStatus.OK);
        }
        return new ResponseEntity<>(new VehicleRouteData(), HttpStatus.OK);
    }
}
