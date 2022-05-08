package com.oneapartment.ams.apartments.dto;

import com.oneapartment.ams.apartments.entity.Apartment;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Data
public class ApartmentUnitRequest {

    @NotNull(message = "Unit Number cannot be NULL")
    private String unitNumber;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String country;
    private String phone;
    private String locationDescription;
    private boolean isOccupied;
    private boolean isRented;
    private  boolean isLeased;
    private boolean isCarParking;
    private UUID ownerId;
    private UUID residentId;
}
