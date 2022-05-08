package com.oneapartment.ams.apartments.controllers;

import com.oneapartment.ams.apartments.dto.ApartmentEventRequestDto;
import com.oneapartment.ams.apartments.dto.ApartmentUnitRequest;
import com.oneapartment.ams.apartments.entity.Apartment;
import com.oneapartment.ams.apartments.entity.ApartmentEvents;
import com.oneapartment.ams.apartments.entity.ApartmentUnit;
import com.oneapartment.ams.apartments.service.ApartmentEventService;
import com.oneapartment.ams.apartments.service.ApartmentUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "api/v1/apartment/events")
@Validated
@Controller
public class ApartmentEventController {

    private ApartmentEventService apartmentEventService;

    @Autowired
    public ApartmentEventController(ApartmentEventService apartmentEventService) {
        this.apartmentEventService = apartmentEventService;
    }

    @PostMapping("/{apartmentId}")
    public ApartmentEvents createNewApartmentEvent(@PathVariable UUID apartmentId, @Valid @RequestBody ApartmentEventRequestDto apartmentEventRequestDto) {
        return this.apartmentEventService.createApartmentEvent(apartmentId,apartmentEventRequestDto);
    }

    @GetMapping("/{apartmentId}")
    public List<ApartmentEvents> getApartmentEvents(@PathVariable UUID apartmentId) {
        return this.apartmentEventService.getAllApartmentEvents(apartmentId);
    }
}
