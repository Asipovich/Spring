package pl.iasipovich.service;
import org.springframework.security.core.userdetails.UserDetailsService;

import pl.iasipovich.model.User;
import pl.iasipovich.dto.UserRegistrationDto;

public interface UserService extends UserDetailsService{
    User save(UserRegistrationDto registrationDto);
}
