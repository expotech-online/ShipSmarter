package org.ahmedukamel.shipsmarter.mapper.user;

import org.ahmedukamel.shipsmarter.dto.user.UserProfileResponse;
import org.ahmedukamel.shipsmarter.model.User;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class UserProfileResponseMapper {
    public UserProfileResponse map(User user) {
        return new UserProfileResponse(
                user.getUsername(),
                user.getEmail(),
                user.getPicture(),
                user.getNumber().toString(),
                user.getName(),
                user.getRegistration(),
                user.getProvider(),
                user.getProviderId(),
                user.getGender(),
                user.getRole(),
                user.getAuthorities(),
                StringUtils.hasLength(user.getPicture()),
                StringUtils.hasLength(user.getNumber().toString())
        );
    }
}