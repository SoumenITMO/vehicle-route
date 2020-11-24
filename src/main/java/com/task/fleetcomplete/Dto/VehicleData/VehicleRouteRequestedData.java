package com.task.fleetcomplete.Dto.VehicleData;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class VehicleRouteRequestedData {

    @NotNull(message = "startDate: can not be null")
    @Pattern(regexp = "^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$", message = "startDate: format is wrong")
    private String startDate;

    @NotNull(message = "endDate: can not be null")
    @Pattern(regexp = "^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$", message = "endDate: format is wrong")
    private String endDate;

    @NotNull(message = "objectId: can not be null")
    @NotEmpty(message = "objectId: can not be empty")
    private String objectId;

    @NotNull(message = "apiKey: can not be null")
    @NotEmpty(message = "apiKey: key can not null")
    private String apiKey;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}
