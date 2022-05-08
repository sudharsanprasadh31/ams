package com.oneapartment.ams.apartments.service;

import com.oneapartment.ams.apartments.AppUser.ApartmentUserPrivilegeRole;
import com.oneapartment.ams.apartments.entity.Apartment;
import com.oneapartment.ams.apartments.entity.AppUser;
import com.oneapartment.ams.apartments.repository.ApartmentRepository;
import com.oneapartment.ams.apartments.repository.ApartmentUserPrivilegeRepository;
import com.oneapartment.ams.apartments.repository.AppUserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class ApartmentService {
    private ApartmentRepository apartmentRepository;
    private ApartmentUserPrivilegeRepository apartmentUserPrivilegeRepository;
    private  ApartmentUserPrivilegeService apartmentUserPrivilegeService;
    private AppUserRepository appUserRepository;

    public List<Apartment> getApartmentByCity(String  city) {

        List<Apartment> apartmentByCity = apartmentRepository.findApartmentsByCity(city);
        if(apartmentByCity.isEmpty()){
            throw new IllegalStateException("No Apartments found for the"+city+"city");
        }

        return apartmentByCity;
    }

    public void createApartment(Apartment apartment) {

        Apartment newApartment = apartmentRepository.save(apartment);

        // create an entry in to the Apartment User privilege and make creator as Admin
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        AppUser userDetails = (AppUser) authentication.getPrincipal();
        Object userDetails = authentication.getPrincipal();
        Optional<AppUser> appUser = appUserRepository.findByEmailAddress(userDetails.toString());
        log.info("Value of authentication.getPrincipal()"+userDetails);
        apartmentUserPrivilegeService.setUserApartmentPrivilege(
                newApartment.getId(),appUser.get().getUserId(), ApartmentUserPrivilegeRole.ROLE_MANAGEMENT
        );
    }
}
