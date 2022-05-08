package com.oneapartment.ams.apartments.repository;

import com.oneapartment.ams.apartments.entity.Apartment;
import com.oneapartment.ams.apartments.entity.ApartmentUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ApartmentUnitRepository extends JpaRepository<ApartmentUnit, UUID> {


    List<ApartmentUnit> findApartmentUnitsById(UUID apartmentId);

    ApartmentUnit findApartmentUnitById(UUID apartmentUnitId);

    boolean existsById(UUID id);

}
