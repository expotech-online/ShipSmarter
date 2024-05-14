package org.ahmedukamel.shipsmarter.controller.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Nonnull;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.ahmedukamel.shipsmarter.annotation.FilesSize;
import org.ahmedukamel.shipsmarter.annotation.NotEmptyFile;
import org.ahmedukamel.shipsmarter.annotation.NotEmptyFiles;
import org.ahmedukamel.shipsmarter.dto.company.CompanyRegistrationRequest;
import org.ahmedukamel.shipsmarter.dto.user.UserRegistrationRequest;
import org.ahmedukamel.shipsmarter.service.auth.AuthService;
import org.ahmedukamel.shipsmarter.service.auth.IAuthService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;

@RestController
@RequestMapping(value = "api/v1/auth")
@Validated
public class AuthController {
    private final IAuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping(value = "personal/register")
    public ResponseEntity<?> userRegistration(@Valid @RequestBody UserRegistrationRequest request) {
        return ResponseEntity.created(URI.create("api/v1/auth/user/register")).body(service.userRegistration(request));
    }

    @PostMapping(value = "company/register")
    public ResponseEntity<?> companyRegistration(@Valid @RequestParam(value = "company") CompanyRegistrationRequest request,
                                                 @NotEmptyFile @RequestParam(value = "logo") MultipartFile logoFile,
                                                 @NotEmptyFile @RequestParam(value = "licence") MultipartFile licenceFile,
                                                 @NotEmptyFiles @FilesSize(size = 5) @RequestParam(value = "pictures") MultipartFile[] picturesFiles) {
        return ResponseEntity.created(URI.create("api/v1/auth/company/register"))
                .body(service.companyRegistration(request, logoFile, licenceFile, picturesFiles));
    }

    @PostMapping(value = "login")
    public ResponseEntity<?> login(@NotBlank @RequestParam(value = "email") String username,
                                   @NotBlank @RequestParam(value = "password") String password,
                                   @RequestParam(value = "device-token", required = false) String deviceToken) {
        return ResponseEntity.ok().body(service.login(username, password, deviceToken));
    }

    @PostMapping(value = "logout")
    public ResponseEntity<?> logout(@NotBlank @RequestParam(value = "token") String token) {
        service.logout(token);
        return ResponseEntity.noContent().build();
    }

    @Component
    @RequiredArgsConstructor
    public static class CompanyRegistrationRequestConverter implements Converter<String, CompanyRegistrationRequest> {
        final ObjectMapper mapper;

        @SneakyThrows
        @Override
        public CompanyRegistrationRequest convert(@Nonnull String source) {
            return this.mapper.readValue(source, CompanyRegistrationRequest.class);
        }
    }
}