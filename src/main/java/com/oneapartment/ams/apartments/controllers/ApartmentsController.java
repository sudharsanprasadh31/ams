package com.oneapartment.ams.apartments.controllers;

import com.oneapartment.ams.apartments.service.ApartmentService;
import com.oneapartment.ams.apartments.entity.Apartment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/apartmentList")
@Validated
@Controller
public class ApartmentsController {
    private ApartmentService apartmentService;

    @Autowired
    public ApartmentsController(ApartmentService apartmentService) {
        this.apartmentService = apartmentService;
    }

    @GetMapping
    public List<Apartment> getApartmentListByCity(@RequestBody Apartment apartment) {
        return apartmentService.getApartmentByCity(apartment.getCity());
    }

    @PostMapping
    public void createNewApartment(@Valid @RequestBody Apartment apartment) {
        this.apartmentService.createApartment(apartment);
    }

}
