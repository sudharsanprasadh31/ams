package com.oneapartment.ams.apartments.controllers;

import com.google.gson.JsonObject;
import com.oneapartment.ams.apartments.dto.NewUserRegistrationResponse;
import com.oneapartment.ams.apartments.dto.RegistrationRequest;
import com.oneapartment.ams.apartments.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;


@RestController
@RequestMapping(path="api/v1/registration")
@AllArgsConstructor
@Validated
public class RegistrationController {

    private RegistrationService registrationService;

    @PostMapping
    public NewUserRegistrationResponse register(@Valid @RequestBody RegistrationRequest registrationRequest) {
        return registrationService.register(registrationRequest);
    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") UUID token) {

        return registrationService.confirmToken(token);
    }
}
