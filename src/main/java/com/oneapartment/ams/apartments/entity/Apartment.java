package com.oneapartment.ams.apartments.entity;

import lombok.*;
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
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
//@EntityListeners(AuditingEntityListener.class)
public class Apartment { //extends Auditable {
    @Id
    private UUID id=UUID.randomUUID();
    @NotNull(message = "Apartment Name Cannot be NULL")
    @NotNull(message = "Apartment Name Cannot be Blank")
    private String name;
    private String city;
    private String state;
    private String postalCode;
    private String country;

    public Apartment(String name, String city, String state, String postalCode, String country) {
        this.name = name;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.country = country;
    }

}
