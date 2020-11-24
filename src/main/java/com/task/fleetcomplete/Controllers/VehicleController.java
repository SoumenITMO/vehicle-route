package com.task.fleetcomplete.Controllers;

import com.task.fleetcomplete.Dto.VehicleData.VehicleRouteRequestedData;
import com.task.fleetcomplete.Services.VehicleServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.*;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class VehicleController {

    @Autowired
    VehicleServices vehicleServices;

    @RequestMapping(value = "getvehicles/{apikey}", method = RequestMethod.GET)
    public ResponseEntity getVehicles(@PathVariable("apikey")String apikey) {

        return vehicleServices.getVehicles(apikey);
    }

    @RequestMapping(value = "vehicleroutes", method = RequestMethod.POST)
    public ResponseEntity<?> getVehicleRoute(@Valid @RequestBody VehicleRouteRequestedData vehicleRouteRequestedData,
                                             Errors errors) {
        if(errors.hasErrors()) {
            List<String> errorsList = errors.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errorsList);
        }
        else {
            return vehicleServices.vehicleRoute(vehicleRouteRequestedData.getApiKey(), vehicleRouteRequestedData.getObjectId(),
                    vehicleRouteRequestedData.getStartDate(), vehicleRouteRequestedData.getEndDate());
        }
    }
}
