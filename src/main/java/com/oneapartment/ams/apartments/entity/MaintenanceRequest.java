package com.oneapartment.ams.apartments.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="maintenance_request")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString(exclude = "apartmentUnit")
@EntityListeners(AuditingEntityListener.class)
public class MaintenanceRequest {
    @Id
    private UUID id=UUID.randomUUID();
    @ManyToOne
    @JoinColumn(name = "apartment_unit_id")
    @JsonIgnore private ApartmentUnit apartmentUnit;
    private String issueSeverity;
    private String phoneNumber;
    private String problemSummary;
    private String problemDescription;
    private String status;
    private String assignedTo;
    private String comments;


    public MaintenanceRequest(ApartmentUnit apartmentUnit, String issueSeverity, String phoneNumber, String problemSummary, String problemDescription, String status, String assignedTo, String comments) {
        this.apartmentUnit = apartmentUnit;
        this.issueSeverity = issueSeverity;
        this.phoneNumber = phoneNumber;
        this.problemSummary = problemSummary;
        this.problemDescription = problemDescription;
        this.status = status;
        this.assignedTo = assignedTo;
        this.comments = comments;
    }

}
