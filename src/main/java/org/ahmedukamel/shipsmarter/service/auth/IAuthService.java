package org.ahmedukamel.shipsmarter.service.auth;

import org.springframework.web.multipart.MultipartFile;

public interface IAuthService {
    Object userRegistration(Object object);

    Object companyRegistration(Object object, MultipartFile logoFile, MultipartFile licenceFile, MultipartFile[] picturesFiles);

    Object login(String username, String password, String deviceToken);

    void logout(String token);
}