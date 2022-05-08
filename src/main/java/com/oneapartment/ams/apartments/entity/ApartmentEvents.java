package com.oneapartment.ams.apartments.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="apartment_events")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@EntityListeners(AuditingEntityListener.class)
public class ApartmentEvents {
    @Id
    private UUID id=UUID.randomUUID();
    @ManyToOne
    @JoinColumn(name = "apartment_id",nullable = false)
    @JsonIgnore private Apartment  apartment;
    private String name;
    private String description;
    private String venue;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm", iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime startDateTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm", iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime endDateTime;
    private String eventOrganizer;


    public ApartmentEvents(Apartment apartment, String name, String description, String venue, LocalDateTime startDateTime, LocalDateTime endDateTime, String eventOrganizer) {
       this.apartment = apartment;
        this.name = name;
        this.description = description;
        this.venue = venue;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.eventOrganizer = eventOrganizer;
    }
}
