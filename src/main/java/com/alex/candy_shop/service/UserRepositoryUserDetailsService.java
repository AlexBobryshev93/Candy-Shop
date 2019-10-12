package com.alex.candy_shop.service;

import com.alex.candy_shop.entities.User;
import com.alex.candy_shop.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserRepositoryUserDetailsService implements UserDetailsService {
    private UserRepo userRepo;

    @Autowired
    public UserRepositoryUserDetailsService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String firstName) throws UsernameNotFoundException {
        User user = userRepo.findByFirstName(firstName);
        if (user != null) {
            return user;
        }
        throw new UsernameNotFoundException(
                "User '" + firstName + "' not found");
    }
}
