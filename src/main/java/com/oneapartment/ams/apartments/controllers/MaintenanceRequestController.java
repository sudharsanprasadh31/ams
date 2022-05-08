package com.oneapartment.ams.apartments.controllers;

import com.oneapartment.ams.apartments.dto.ApartmentEventRequestDto;
import com.oneapartment.ams.apartments.dto.MaintenanceRequestDto;
import com.oneapartment.ams.apartments.entity.ApartmentEvents;
import com.oneapartment.ams.apartments.entity.MaintenanceRequest;
import com.oneapartment.ams.apartments.service.ApartmentEventService;
import com.oneapartment.ams.apartments.service.MaintenanceRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "api/v1/apartment/maintenance")
@Validated
@Controller
public class MaintenanceRequestController {

    private MaintenanceRequestService maintenanceRequestService;

    @Autowired
    public MaintenanceRequestController(MaintenanceRequestService maintenanceRequestService) {
        this.maintenanceRequestService = maintenanceRequestService;
    }

    @PostMapping("/{apartmentUnitId}")
    public MaintenanceRequest createNewMaintenanceRequest(@PathVariable UUID apartmentUnitId, @Valid @RequestBody MaintenanceRequestDto maintenanceRequestDto) {
        return this.maintenanceRequestService.createMaintenanceRequest(apartmentUnitId,maintenanceRequestDto);
    }

    @GetMapping("/{apartmentUnitId}")
    public List<MaintenanceRequest> getApartmentEvents(@PathVariable UUID apartmentUnitId) {
        return this.maintenanceRequestService.getAllMaintenanceRequestByUnit(apartmentUnitId);
    }
}
