package com.oneapartment.ams.apartments.repository;

import com.oneapartment.ams.apartments.entity.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ApartmentRepository extends JpaRepository<Apartment, UUID> {


    List<Apartment> findApartmentsByCity(String city);

    Apartment findApartmentsById(UUID id);

    boolean existsById(UUID id);
}
