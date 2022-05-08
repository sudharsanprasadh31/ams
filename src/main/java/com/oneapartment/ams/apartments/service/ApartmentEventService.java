package com.oneapartment.ams.apartments.service;

import com.oneapartment.ams.apartments.AppUser.ApartmentUserPrivilegeRole;
import com.oneapartment.ams.apartments.AppUser.AppUserRole;
import com.oneapartment.ams.apartments.dto.ApartmentEventRequestDto;
import com.oneapartment.ams.apartments.dto.ApartmentUnitRequest;
import com.oneapartment.ams.apartments.entity.Apartment;
import com.oneapartment.ams.apartments.entity.ApartmentEvents;
import com.oneapartment.ams.apartments.entity.ApartmentUnit;
import com.oneapartment.ams.apartments.entity.AppUser;
import com.oneapartment.ams.apartments.repository.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
public class ApartmentEventService {

    private ApartmentEventRepository apartmentEventRepository;
    private ApartmentRepository apartmentRepository;
    private ApartmentUserPrivilegeRepository apartmentUserPrivilegeRepository;
    private AppUserRepository appUserRepository;

    public List<ApartmentEvents> getAllApartmentEvents(UUID apartmentId) {

        if (apartmentRepository.existsById(apartmentId)) {

            Apartment apartment = apartmentRepository.findApartmentsById(apartmentId);
            return apartmentEventRepository.findAllByApartment(apartment);
        } else {

            throw new IllegalStateException("No Apartments found with the Apartment ID" + apartmentId);
        }

    }

    public List<ApartmentEvents> getFutureApartmentEvents(UUID apartmentId) {

        if (apartmentRepository.existsById(apartmentId)) {

            return apartmentEventRepository.findAllByEndDateTimeBefore(LocalDateTime.now());
        } else {

            throw new IllegalStateException("No Apartments found with the Apartment ID" + apartmentId);
        }

    }

    public ApartmentEvents createApartmentEvent(UUID apartmentId, ApartmentEventRequestDto apartmentEventRequestDto) {

        if(apartmentEventRequestDto.getEndDateTime().isAfter(apartmentEventRequestDto.getStartDateTime())){
            if (apartmentRepository.existsById(apartmentId)) {
                Optional<AppUser> appUser = appUserRepository.findByEmailAddress(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
                Apartment apartment = apartmentRepository.findApartmentsById(apartmentId);
                // New apartment event can be created by Apartment Management or App Admin
                if(apartmentUserPrivilegeRepository.existsApartmentUserPrivilegeByApartmentAndAppUserAndApartmentUserPrivilegeRole(apartment,appUser, ApartmentUserPrivilegeRole.ROLE_MANAGEMENT )||appUser.get().getAppUserRole()== AppUserRole.ROLE_ADMIN){
                    ApartmentEvents apartmentEvents = new ApartmentEvents(apartment, apartmentEventRequestDto.getName(),
                            apartmentEventRequestDto.getDescription(),
                            apartmentEventRequestDto.getVenue(),apartmentEventRequestDto.getStartDateTime(),
                            apartmentEventRequestDto.getEndDateTime(),apartmentEventRequestDto.getEventOrganizer());
                    return apartmentEventRepository.save(apartmentEvents);
                }else{
                    throw new IllegalStateException("You either don't belong to the apartment or does NOT have ADMIN privilege: ");
                }
            } else {
                throw new IllegalStateException("No Apartments found with the Apartment ID" + apartmentId);
            }
        }else{
            throw new IllegalStateException("Event End Date cannot be before Start Date" + apartmentId);
        }

    }
}
