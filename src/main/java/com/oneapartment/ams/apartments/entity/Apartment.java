package com.oneapartment.ams.apartments.entity;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

import static javax.persistence.TemporalType.TIMESTAMP;


@Entity
@Table
//@EntityListeners(AuditingEntityListener.class)
public class Apartment {//extends Auditable {
    @Id
    private UUID id=UUID.randomUUID();
    @NotNull(message = "Apartment Name Cannot be NULL")
    @NotNull(message = "Apartment Name Cannot be Blank")
    private String name;
    private String city;
    private String state;
    private String postalCode;
    private String country;

    public Apartment() {
    }

    public Apartment(UUID id, String name, String city, String state, String postalCode, String country) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.country = country;
    }

    public Apartment(String name, String city, String state, String postalCode, String country) {
        this.name = name;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.country = country;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

//    public UUID getCreatedBy() {
//        return createdBy;
//    }
//
//    public void setCreatedBy(UUID createdBy) {
//        this.createdBy = createdBy;
//    }

    @Override
    public String toString() {
        return "ApartmentList{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
