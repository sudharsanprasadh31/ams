package com.oneapartment.ams.apartments.service;

import com.oneapartment.ams.apartments.entity.ConfirmationToken;
import com.oneapartment.ams.apartments.repository.ConfirmationTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {

    private ConfirmationTokenRepository confirmationTokenRepository;

    public ConfirmationToken saveConfirmationToken(ConfirmationToken token){

        return confirmationTokenRepository.save(token);
    }

    public int setConfirmedAt(UUID token){

        return confirmationTokenRepository.updateConfirmedAt(token, LocalDateTime.now());
    }

    public Optional<ConfirmationToken> getToken(UUID token){
         return confirmationTokenRepository.findByToken(token);
    }
}
