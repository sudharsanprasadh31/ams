package com.oneapartment.ams.apartments.repository;

import com.oneapartment.ams.apartments.AppUser.ApartmentUserPrivilegeRole;
import com.oneapartment.ams.apartments.entity.Apartment;
import com.oneapartment.ams.apartments.entity.ApartmentUserPrivilege;
import com.oneapartment.ams.apartments.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ApartmentUserPrivilegeRepository extends JpaRepository<ApartmentUserPrivilege, UUID> {


    List<ApartmentUserPrivilege> findApartmentUserPrivilegeByAppUser(UUID appUserId);

    ApartmentUserPrivilege findApartmentUserPrivilegeByAppUserAndAndApartment(UUID appUserId, UUID apartmentId);

    List<ApartmentUserPrivilege> findApartmentUserPrivilegeByApartmentAndApartmentUserPrivilegeRole(
            UUID apartmentId, ApartmentUserPrivilegeRole apartmentUserPrivilegeRole);

    @Query("SELECT COUNT(a.id) =1 FROM ApartmentUserPrivilege a WHERE a.apartment = ?1 AND a.appUser=?2 AND a.apartmentUserPrivilegeRole =?3")
    boolean existsApartmentUserPrivilegeByApartmentAndAppUserAndApartmentUserPrivilegeRole(
            Apartment apartment, Optional<AppUser> appUser, ApartmentUserPrivilegeRole role);


}
