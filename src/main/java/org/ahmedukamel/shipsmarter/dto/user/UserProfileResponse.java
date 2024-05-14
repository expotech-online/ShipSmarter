package org.ahmedukamel.shipsmarter.dto.user;

import org.ahmedukamel.shipsmarter.model.Name;
import org.ahmedukamel.shipsmarter.model.Gender;
import org.ahmedukamel.shipsmarter.model.Provider;
import org.ahmedukamel.shipsmarter.model.Role;
import org.springframework.security.core.GrantedAuthority;

import java.time.ZonedDateTime;
import java.util.Collection;

public record UserProfileResponse(
        String username,
        String email,
        String picture,
        String number,
        Name name,
        ZonedDateTime registration,
        Provider provider,
        String providerId,
        Gender gender,
        Role role,
        Collection<? extends GrantedAuthority> authorities,
        boolean hasPicture,
        boolean hasNumber
) {
}