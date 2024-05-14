package org.ahmedukamel.shipsmarter.service.impl;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.shipsmarter.repository.UserRepository;
import org.ahmedukamel.shipsmarter.service.db.DatabaseService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return DatabaseService.get(repository::findByLocalEmail, username, User.class);
    }
}