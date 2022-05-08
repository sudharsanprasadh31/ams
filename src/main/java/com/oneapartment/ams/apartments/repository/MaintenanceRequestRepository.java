package com.oneapartment.ams.apartments.repository;

import com.oneapartment.ams.apartments.entity.ApartmentUnit;
import com.oneapartment.ams.apartments.entity.MaintenanceRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MaintenanceRequestRepository extends JpaRepository<MaintenanceRequest, UUID> {


    List<MaintenanceRequest> findMaintenanceRequestByApartmentUnit_Id(UUID apartmentUnitId);

}
