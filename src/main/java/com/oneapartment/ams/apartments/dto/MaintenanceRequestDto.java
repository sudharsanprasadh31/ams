package com.oneapartment.ams.apartments.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.oneapartment.ams.apartments.entity.ApartmentUnit;
import lombok.*;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Data
public class MaintenanceRequestDto {

    private String issueSeverity;
    private String phoneNumber;
    @NotNull(message = "Summary cannot be NULL")
    private String problemSummary;
    @NotNull(message = "Description cannot be NULL")
    private String problemDescription;
    private String status;
    private String assignedTo;
    private String comments;
}
