package org.ahmedukamel.shipsmarter.dto.company;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;
import org.ahmedukamel.shipsmarter.constant.RegexConstants;

import java.util.Collection;

@Data
public class CompanyRegistrationRequest {
    @NotBlank
    @Email
    String email;

    @NotBlank
    @Pattern(regexp = RegexConstants.PASSWORD)
    String password;

    @NotBlank
    @Pattern(regexp = RegexConstants.NAME)
    String firstName;

    @NotBlank
    @Pattern(regexp = RegexConstants.NAME)
    String lastName;

    String deviceToken;

    @NotBlank
    @Email
    String companyEmail;

    @NotBlank
    String companyPhone;

    @NotBlank
    String name;

    @NotNull
    Double latitude;

    @NotNull
    Double longitude;

    @NotNull
    Integer zipCode;

    @NotBlank
    String taxNumber;

    @NotBlank
    String about;

    @NotBlank
    String description;

    @NotBlank
    String advantages;

    @NotBlank
    String disadvantages;

    @NotNull
    Double distanceCost;

    @NotNull
    Double weightCost;

    @NotNull
    Double volumeCost;

    @NotEmpty
    @Valid
    Collection<TrackRequest> tracks;

    @NotEmpty
    @Valid
    Collection<WorkHourDto> workHours;
}