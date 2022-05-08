package com.oneapartment.ams.apartments.repository;

import com.oneapartment.ams.apartments.entity.Apartment;
import com.oneapartment.ams.apartments.entity.ApartmentEvents;
import com.oneapartment.ams.apartments.entity.ApartmentUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface ApartmentEventRepository extends JpaRepository<ApartmentEvents, UUID> {


    List<ApartmentEvents> findAllByEndDateTimeBefore(LocalDateTime LocalDateTime);

    List<ApartmentEvents> findAllByApartment(Apartment apartment);

}
