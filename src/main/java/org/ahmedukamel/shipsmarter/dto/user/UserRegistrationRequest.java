package org.ahmedukamel.shipsmarter.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.ahmedukamel.shipsmarter.constant.RegexConstants;

public record UserRegistrationRequest(
        @NotBlank
        @Email
        String email,

        @NotBlank
        @Pattern(regexp = RegexConstants.PASSWORD)
        String password,

        @NotBlank
        @Pattern(regexp = RegexConstants.NAME)
        String firstName,

        @NotBlank
        @Pattern(regexp = RegexConstants.NAME)
        String lastName,

        String deviceToken
) {
}