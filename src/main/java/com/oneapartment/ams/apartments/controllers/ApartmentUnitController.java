package com.oneapartment.ams.apartments.controllers;

import com.oneapartment.ams.apartments.dto.ApartmentUnitRequest;
import com.oneapartment.ams.apartments.entity.Apartment;
import com.oneapartment.ams.apartments.entity.ApartmentUnit;
import com.oneapartment.ams.apartments.service.ApartmentService;
import com.oneapartment.ams.apartments.service.ApartmentUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "api/v1/apartment")
@Validated
@Controller
public class ApartmentUnitController {
    private ApartmentUnitService apartmentUnitService;

    @Autowired
    public ApartmentUnitController(ApartmentUnitService apartmentUnitService) {
        this.apartmentUnitService = apartmentUnitService;
    }


    @PostMapping("/{apartmentId}")
    public ApartmentUnit createNewApartmentUnit(@PathVariable UUID apartmentId,@Valid @RequestBody ApartmentUnitRequest apartmentUnitRequest) {
        return this.apartmentUnitService.createApartmentUnit(apartmentId,apartmentUnitRequest);
    }

    @GetMapping("/{apartment_unit_id}")
    public List<ApartmentUnit> getApartmentUnits(@PathVariable UUID apartment_unit_id) {
        return this.apartmentUnitService.getAllApartmentUnits(apartment_unit_id);
    }
}
