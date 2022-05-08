package com.oneapartment.ams.apartments.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.oneapartment.ams.apartments.AppUser.AppUserRole;
import com.oneapartment.ams.apartments.entity.ConfirmationToken;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Data
public class NewUserRegistrationResponse {

    private String firstName;
    private String lastName;
    private String emailAddress;
    @JsonIgnore
    private String password;
    private AppUserRole appUserRole;
    private ConfirmationToken confirmationToken;
}
