package com.oneapartment.ams.apartments.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
@Data
public class RegistrationRequest {
    @NotNull(message = "First Name cannot be NULL")
    @NotBlank(message = "First Name cannot be Blank")
    private String firstName;
    @NotNull(message = "Last Name cannot be NULL")
    @NotBlank(message = "Last Name cannot be Blank")
    private String lastName;
    @Email(message = "Email Address is NOT in the correct format")
    @NotNull(message = "Email Address cannot be NULL")
    @NotBlank(message = "Email Address cannot be Blank")
    private String emailAddress;
    @NotNull(message = "Password cannot be NULL")
    @NotBlank(message = "Password cannot be Blank")
    private String password;
}
