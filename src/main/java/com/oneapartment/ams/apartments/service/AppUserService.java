package com.oneapartment.ams.apartments.service;

import com.oneapartment.ams.apartments.entity.AppUser;
import com.oneapartment.ams.apartments.entity.ConfirmationToken;
import com.oneapartment.ams.apartments.repository.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {


    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    private final static String USER_NOT_FOUND = "user with email %s is not found";

    @Override
    public UserDetails loadUserByUsername(String emailAddress) throws UsernameNotFoundException {

        return appUserRepository.findByEmailAddress(emailAddress)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND, emailAddress)));
    }

    public String registerUser(AppUser appUser) {
        boolean isUserExist = appUserRepository.findByEmailAddress(appUser.getEmailAddress()).isPresent();
        if (isUserExist) {
            throw new IllegalStateException("Emails address already registered");
        }
        String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());
        System.out.println("Encoded Password is " + encodedPassword);
        appUser.setPassword(encodedPassword);
        appUserRepository.save(appUser);
        ConfirmationToken token = new ConfirmationToken(
                UUID.randomUUID(),
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(7),
                appUser
        );
        confirmationTokenService.saveConfirmationToken(token);
//        ToDo: Send Email
        return token.getToken().toString();
    }

    public int enableAppUser(String email) {
        return appUserRepository.enableAppUser(email);
    }
}
