package com.oneapartment.ams.apartments.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="apartment_unit")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString(exclude = "apartment")
@EntityListeners(AuditingEntityListener.class)
public class ApartmentUnit {

    @Id
    private UUID id = UUID.randomUUID();
    @ManyToOne//(fetch = FetchType.EAGER)
    @JoinColumn(name="apartment_id", nullable = false) // insertable = false, updatable = false)
    @JsonIgnore private Apartment apartment;
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

    @OneToMany(mappedBy = "apartmentUnit",cascade = CascadeType.ALL)
   // @JoinColumn(name="apartment_unit_id")
    @JsonIgnore private List<MaintenanceRequest> maintenanceRequests;

    public ApartmentUnit(Apartment apartment, String unitNumber, String addressLine1, String addressLine2, String city, String state, String country, String phone, String locationDescription, boolean isOccupied, boolean isRented, boolean isLeased, boolean isCarParking, UUID ownerId, UUID residentId) {
        this.apartment = apartment;
        this.unitNumber = unitNumber;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.state = state;
        this.country = country;
        this.phone = phone;
        this.locationDescription = locationDescription;
        this.isOccupied = isOccupied;
        this.isRented = isRented;
        this.isLeased = isLeased;
        this.isCarParking = isCarParking;
        this.ownerId = ownerId;
        this.residentId = residentId;
    }
}
