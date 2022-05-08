package com.oneapartment.ams.apartments.service;

import com.oneapartment.ams.apartments.AppUser.ApartmentUserPrivilegeRole;
import com.oneapartment.ams.apartments.AppUser.AppUserRole;
import com.oneapartment.ams.apartments.dto.ApartmentUnitRequest;
import com.oneapartment.ams.apartments.entity.Apartment;
import com.oneapartment.ams.apartments.entity.ApartmentUnit;
import com.oneapartment.ams.apartments.entity.ApartmentUserPrivilege;
import com.oneapartment.ams.apartments.entity.AppUser;
import com.oneapartment.ams.apartments.repository.ApartmentRepository;
import com.oneapartment.ams.apartments.repository.ApartmentUnitRepository;
import com.oneapartment.ams.apartments.repository.ApartmentUserPrivilegeRepository;
import com.oneapartment.ams.apartments.repository.AppUserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
public class ApartmentUnitService {

    private ApartmentUnitRepository apartmentUnitRepository;
    private ApartmentRepository apartmentRepository;
    private ApartmentUserPrivilegeRepository apartmentUserPrivilegeRepository;
    private AppUserRepository appUserRepository;

    public List<ApartmentUnit> getAllApartmentUnits(UUID apartmentUnitId) {
        List<ApartmentUnit> apartmentUnits = apartmentUnitRepository.findApartmentUnitsById(apartmentUnitId);
        if (apartmentUnits.isEmpty()) {
            throw new IllegalStateException("No Apartments Unit found for the ID" + apartmentUnitId);
        }

        return apartmentUnits;
    }

    public ApartmentUnit createApartmentUnit(UUID apartmentId, ApartmentUnitRequest apartmentUnitRequest) {

        if(apartmentRepository.existsById(apartmentId)){

            Apartment apartment = apartmentRepository.findApartmentsById(apartmentId);

            Optional<AppUser> appUser = appUserRepository.findByEmailAddress(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());

            // New apartment unit can be created by Apartment Management or App Admin
                if(apartmentUserPrivilegeRepository.existsApartmentUserPrivilegeByApartmentAndAppUserAndApartmentUserPrivilegeRole(apartment,appUser,ApartmentUserPrivilegeRole.ROLE_MANAGEMENT )||appUser.get().getAppUserRole()== AppUserRole.ROLE_ADMIN){
                    ApartmentUnit apartmentUnit = new ApartmentUnit(apartment, apartmentUnitRequest.getUnitNumber(),
                            apartmentUnitRequest.getAddressLine1(), apartmentUnitRequest.getAddressLine2(),
                            apartmentUnitRequest.getCity(), apartmentUnitRequest.getState(),
                            apartmentUnitRequest.getCountry(), apartmentUnitRequest.getPhone(),
                            apartmentUnitRequest.getLocationDescription(),
                            apartmentUnitRequest.isOccupied(),
                            apartmentUnitRequest.isRented(), apartmentUnitRequest.isLeased(),
                            apartmentUnitRequest.isCarParking(), apartmentUnitRequest.getOwnerId(),
                            apartmentUnitRequest.getResidentId());
                    return apartmentUnitRepository.save(apartmentUnit);
                }else{
                    throw new IllegalStateException("You either don't belong to the apartment or does NOT have ADMIN privilege: ");
                }


        }else{
            throw new IllegalStateException("No apartment found for the ID: "+apartmentId);
        }
    }
}
