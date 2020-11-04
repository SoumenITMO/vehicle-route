package com.task.fleetcomplete.Controllers;

import com.task.fleetcomplete.Services.VehicleServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

@RestController
public class VehicleController {

    @Autowired
    VehicleServices vehicleServices;

    @RequestMapping(value = "getvehicles/{apikey}", method = RequestMethod.GET)
    public ResponseEntity getVehicles(@PathVariable("apikey")String apikey) {

        return vehicleServices.getVehicles(apikey);
    }

    @RequestMapping(value = "getvehicleroute/{apikey}/{objectid}/{startdate}/{enddate}", method = RequestMethod.GET)
    public ResponseEntity getVehicleRoute(@PathVariable("apikey")String apiKey, @PathVariable("objectid")String objectId,
                                          @PathVariable("startdate")String startDate, @PathVariable("enddate")String endDate) {

        return vehicleServices.vehicleRoute(apiKey, objectId, startDate, endDate);
    }
}
