package com.oneapartment.ams.apartments.service;

import com.oneapartment.ams.apartments.AppUser.ApartmentUserPrivilegeRole;
import com.oneapartment.ams.apartments.AppUser.AppUserRole;
import com.oneapartment.ams.apartments.dto.MaintenanceRequestDto;
import com.oneapartment.ams.apartments.entity.*;
import com.oneapartment.ams.apartments.repository.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
public class MaintenanceRequestService {

    private MaintenanceRequestRepository maintenanceRequestRepository;
    private ApartmentUnitRepository apartmentUnitRepository;
    private ApartmentUserPrivilegeRepository apartmentUserPrivilegeRepository;
    private AppUserRepository appUserRepository;
    private ApartmentRepository apartmentRepository;

    @Autowired
    public MaintenanceRequestService(ApartmentUnitRepository apartmentUnitRepository, MaintenanceRequestRepository maintenanceRequestRepository) {
        this.apartmentUnitRepository = apartmentUnitRepository;
        this.maintenanceRequestRepository = maintenanceRequestRepository;
    }

    public List<MaintenanceRequest> getAllMaintenanceRequestByUnit(UUID apartmentUnitId) {

        if (apartmentUnitRepository.existsById(apartmentUnitId)) {
            return maintenanceRequestRepository.findMaintenanceRequestByApartmentUnit_Id(apartmentUnitId);
        } else {
            throw new IllegalStateException("No Apartments Unit found for the ID" + apartmentUnitId);
        }

    }

    public MaintenanceRequest createMaintenanceRequest(UUID apartmentUnitId, MaintenanceRequestDto maintenanceRequestDto) {

        if (apartmentUnitRepository.existsById(apartmentUnitId)) {
            ApartmentUnit apartmentUnit = apartmentUnitRepository.findApartmentUnitById(apartmentUnitId);
            UUID apartmentId = apartmentUnit.getApartment().getId();
            if (apartmentRepository.existsById(apartmentId)) {
                Optional<AppUser> appUser = appUserRepository.findByEmailAddress(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
                Apartment apartment = apartmentRepository.findApartmentsById(apartmentId);
                // New Maintenance Request can be created by Apartment Management or App Admin
                if(apartmentUserPrivilegeRepository.existsApartmentUserPrivilegeByApartmentAndAppUserAndApartmentUserPrivilegeRole(apartment,appUser, ApartmentUserPrivilegeRole.ROLE_MANAGEMENT )||appUser.get().getAppUserRole()== AppUserRole.ROLE_ADMIN){
                    MaintenanceRequest maintenanceRequest = new MaintenanceRequest(apartmentUnit, maintenanceRequestDto.getIssueSeverity(),
                            maintenanceRequestDto.getPhoneNumber(), maintenanceRequestDto.getProblemSummary(),
                            maintenanceRequestDto.getProblemDescription(), maintenanceRequestDto.getStatus(), maintenanceRequestDto.getAssignedTo()
                            , maintenanceRequestDto.getComments());
                    return maintenanceRequestRepository.save(maintenanceRequest);
                }else{
                    throw new IllegalStateException("You either don't belong to the apartment or does NOT have ADMIN privilege: ");
                }
            } else {
                throw new IllegalStateException("No Apartments found with the Apartment ID" + apartmentId);
            }
        } else {
            throw new IllegalStateException("No Apartments Unit found for the ID " + apartmentUnitId);
        }

    }
}
