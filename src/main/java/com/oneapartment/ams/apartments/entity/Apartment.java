package com.oneapartment.ams.apartments.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
import java.util.List;
import java.util.UUID;



@Entity
@Table(name = "apartment")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString(exclude = "apartmentUnit, apartmentEvents , apartmentUserPrivileges")
@EntityListeners(AuditingEntityListener.class)
public class Apartment extends Auditable<String> {
    @Id
    private UUID id = UUID.randomUUID();
    @NotNull(message = "Apartment Name Cannot be NULL")
    @NotNull(message = "Apartment Name Cannot be Blank")
    private String name;
    private String city;
    private String state;
    private String postalCode;
    private String country;

    @OneToMany(mappedBy = "apartment", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ApartmentUnit> apartmentUnit;

    @OneToMany(mappedBy = "apartment", cascade = CascadeType.ALL)
    @JsonIgnore private List<ApartmentEvents> apartmentEvents;

    @OneToMany(mappedBy="apartment",cascade = CascadeType.ALL)
    @JsonIgnore private List<ApartmentUserPrivilege> apartmentUserPrivileges;

    public Apartment(String name, String city, String state,
                     String postalCode, String country) {
        this.name = name;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.country = country;
    }

}
