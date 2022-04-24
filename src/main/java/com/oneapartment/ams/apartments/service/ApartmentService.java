package com.oneapartment.ams.apartments.service;

import com.oneapartment.ams.apartments.entity.Apartment;
import com.oneapartment.ams.apartments.repository.ApartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApartmentService {
    private ApartmentRepository apartmentRepository;

    @Autowired
    public ApartmentService(ApartmentRepository apartmentRepository) {
        this.apartmentRepository = apartmentRepository;
    }

    public List<Apartment> getApartmentList() {
        return apartmentRepository.findAll();
    }

    public List<Apartment> getApartmentByCity(String city) {
        System.out.println("Name of the city is : "+city);
        List<Apartment> apartmentByCity = apartmentRepository.findApartmentsByCity(city);
        if(apartmentByCity.isEmpty()){
            throw new IllegalStateException("No Apartments found for the"+city+"city");
        }

        return apartmentByCity;
    }

    public void createApartment(Apartment apartment) {
        apartmentRepository.save(apartment);
    }
}
