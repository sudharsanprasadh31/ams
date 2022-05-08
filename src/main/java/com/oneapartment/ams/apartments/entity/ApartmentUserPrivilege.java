package com.oneapartment.ams.apartments.entity;

import com.oneapartment.ams.apartments.AppUser.ApartmentUserPrivilegeRole;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name="apartment_user_privilege")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@EntityListeners(AuditingEntityListener.class)
public class ApartmentUserPrivilege extends Auditable<String> {

    @Id
    private UUID id = UUID.randomUUID();

    @ManyToOne
    @JoinColumn(name="app_user_id")
    private AppUser appUser;
    @ManyToOne
    @JoinColumn(name="apartment_id")
    private Apartment apartment;
    @Enumerated(EnumType.STRING)
    private ApartmentUserPrivilegeRole apartmentUserPrivilegeRole;

    public ApartmentUserPrivilege(String createdBy, Date creationDate, String lastModifiedBy, Date lastModifiedDate, AppUser appUser, Apartment apartment, ApartmentUserPrivilegeRole apartmentUserPrivilegeRole) {
        super(createdBy, creationDate, lastModifiedBy, lastModifiedDate);
        this.appUser = appUser;
        this.apartment = apartment;
        this.apartmentUserPrivilegeRole = apartmentUserPrivilegeRole;
    }

    public ApartmentUserPrivilege(AppUser appUser, Apartment apartment, ApartmentUserPrivilegeRole apartmentUserPrivilegeRole) {
        this.appUser = appUser;
        this.apartment = apartment;
        this.apartmentUserPrivilegeRole = apartmentUserPrivilegeRole;
    }
}
