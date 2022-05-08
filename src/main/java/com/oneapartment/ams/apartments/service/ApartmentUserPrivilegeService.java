package com.oneapartment.ams.apartments.service;

import com.oneapartment.ams.apartments.AppUser.ApartmentUserPrivilegeRole;
import com.oneapartment.ams.apartments.entity.Apartment;
import com.oneapartment.ams.apartments.entity.ApartmentUserPrivilege;
import com.oneapartment.ams.apartments.entity.AppUser;
import com.oneapartment.ams.apartments.repository.ApartmentRepository;
import com.oneapartment.ams.apartments.repository.ApartmentUserPrivilegeRepository;
import com.oneapartment.ams.apartments.repository.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ApartmentUserPrivilegeService {

    private ApartmentUserPrivilegeRepository apartmentUserPrivilegeRepository;
    private ApartmentRepository apartmentRepository;
    private AppUserRepository appUserRepository;


    public ApartmentUserPrivilege getUserApartmentPrivilege(UUID appUserId, UUID apartmentId) {

        return apartmentUserPrivilegeRepository.findApartmentUserPrivilegeByAppUserAndAndApartment(appUserId, apartmentId);

    }

    public ApartmentUserPrivilege setUserApartmentPrivilege(UUID apartmentId, UUID appUserId, ApartmentUserPrivilegeRole role) {

        if(apartmentRepository.existsById(apartmentId)){

            Apartment apartment = apartmentRepository.findApartmentsById(apartmentId);

            if(appUserRepository.existsById(appUserId)){

                AppUser appUser = appUserRepository.findAppUserByUserId(appUserId);

                ApartmentUserPrivilege apartmentUserPrivilege = new ApartmentUserPrivilege(appUser,apartment,role);

                return apartmentUserPrivilegeRepository.save(apartmentUserPrivilege);

            }else{
                throw new IllegalStateException("No user found for the ID: "+appUserId);
            }

        }else{
            throw new IllegalStateException("No apartment found for the ID: "+apartmentId);
        }



    }
}
