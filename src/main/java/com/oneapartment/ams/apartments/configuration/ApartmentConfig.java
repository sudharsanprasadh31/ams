package com.oneapartment.ams.apartments.configuration;

import com.oneapartment.ams.apartments.entity.Apartment;
import com.oneapartment.ams.apartments.repository.ApartmentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.UUID;

@Configuration
public class ApartmentConfig {

    @Bean
    CommandLineRunner commandLineRunner(ApartmentRepository apartmentRepository) {
        return args -> {
//            Apartment apartment1 = new Apartment(UUID.fromString("17590041-e260-471f-a3e8-2b38fc1633a3"), "SilentValley"
//                    , "Edison", "NJ", "08837", "US");
//            Apartment apartment2 = new Apartment(UUID.fromString("cd9e1050-d9de-4de4-9039-f1ca9efb14d7"), "SilentValley"
//                    , "Edison", "NJ", "08837", "US");
//            apartmentRepository.saveAll(List.of(apartment1, apartment2));
        };


    }
}
