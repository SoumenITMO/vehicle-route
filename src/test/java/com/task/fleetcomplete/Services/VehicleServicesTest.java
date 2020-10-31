package com.task.fleetcomplete.Services;

import com.task.fleetcomplete.Dto.APIError;
import com.task.fleetcomplete.Dto.VehicleData.VehicleProcessedData;
import com.task.fleetcomplete.Dto.VehicleProcessedData.VehicleRouteData;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class VehicleServicesTest {

    @Autowired
    VehicleServices vehicleServices;

    String apiKey1 = "home.assignment.2-1230927";
    String apiKey2 = "home.assignment-1230927";
    String objectId = "187286";
    String startDate = "2020-10-28";
    String endDate = "2020-10-29";

    @Test
    public void getVehiclesWithCorrectAPIKeyTest() {
        List<VehicleProcessedData> vehicleProcessedDataList =
                (List<VehicleProcessedData>)vehicleServices.getVehicles(apiKey1).getBody();
        Assert.assertNotNull(vehicleProcessedDataList);
    }

    @Test
    public void getVehiclesWithIncorrectAPIKeyTest() {
        APIError apiError = (APIError)vehicleServices.getVehicles(apiKey2).getBody();
        Assert.assertEquals(101, apiError.getStatusCode());
    }

    @Test
    public void getVehicleRouteWithCorrectAPIKeyTest() {
        VehicleRouteData vehicleRouteData = (VehicleRouteData)vehicleServices.vehicleRoute(apiKey1,
                objectId, startDate, endDate).getBody();
        Assert.assertNotNull(vehicleRouteData);
    }

    @Test
    public void getVehicleRouteErrorCodeWithIncorrectAPIKeyTest() {
        APIError vehicleRouteAPIError = (APIError)vehicleServices.vehicleRoute(apiKey2,
                objectId, startDate, endDate).getBody();
        Assert.assertEquals(101, vehicleRouteAPIError.getStatusCode());
    }
}