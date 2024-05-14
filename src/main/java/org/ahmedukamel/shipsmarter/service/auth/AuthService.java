package org.ahmedukamel.shipsmarter.service.auth;

import com.google.i18n.phonenumbers.PhoneNumberUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.ahmedukamel.shipsmarter.constant.PathConstants;
import org.ahmedukamel.shipsmarter.dto.api.ApiResponse;
import org.ahmedukamel.shipsmarter.dto.company.CompanyRegistrationRequest;
import org.ahmedukamel.shipsmarter.dto.company.CompanyResponse;
import org.ahmedukamel.shipsmarter.dto.user.UserProfileResponse;
import org.ahmedukamel.shipsmarter.dto.user.UserRegistrationRequest;
import org.ahmedukamel.shipsmarter.mapper.company.CompanyResponseMapper;
import org.ahmedukamel.shipsmarter.mapper.user.UserProfileResponseMapper;
import org.ahmedukamel.shipsmarter.model.*;
import org.ahmedukamel.shipsmarter.repository.*;
import org.ahmedukamel.shipsmarter.service.db.DatabaseService;
import org.ahmedukamel.shipsmarter.service.image.IImageService;
import org.ahmedukamel.shipsmarter.service.token.IAccessTokenService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.Function;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService implements IAuthService {
    final AuthenticationManager authenticationManager;
    final UserProfileResponseMapper userMapper;
    final CompanyResponseMapper companyMapper;
    final CompanyRepository companyRepository;
    final IAccessTokenService tokenService;
    final UserRepository userRepository;
    final IImageService imageService;
    final TrackRepository trackRepository;
    final RegionRepository regionRepository;
    final WorkHourRepository workHourRepository;
    final PasswordEncoder passwordEncoder;

    Function<String, String> usernameProvider = (email) -> "%s%d"
            .formatted(email.strip().toLowerCase().split("@")[0],
                    1_000 + new Random().nextInt(9_000));

    @Override
    public Object userRegistration(Object object) {
        UserRegistrationRequest request = (UserRegistrationRequest) object;
        User savedUser = getSavedUser(request.email(), request.password(), request.firstName(), request.lastName(), request.deviceToken());
        // TODO: Account Activation
        UserProfileResponse response = userMapper.map(savedUser);
        return new ApiResponse(true, "Successful Registration.", response);
    }

    @SneakyThrows
    @Override
    public Object companyRegistration(Object object, MultipartFile logoFile, MultipartFile licenceFile, MultipartFile[] picturesFiles) {
        CompanyRegistrationRequest request = (CompanyRegistrationRequest) object;

        User savedUser = getSavedUser(request.getEmail(), request.getPassword(), request.getFirstName(), request.getLastName(), request.getDeviceToken());

        Company company = new Company();
        company.setEmail(request.getCompanyEmail());
        company.setLocation(new Location(request.getLatitude(), request.getLongitude()));
        company.setNumber(new PhoneNumber(PhoneNumberUtil.getInstance().parse(request.getCompanyPhone(), "EG")));
        company.setOwner(savedUser);
        company.setName(request.getName().strip());
        company.setAbout(request.getAbout().strip());
        company.setDescription(request.getDescription().strip());
        company.setAdvantages(request.getAdvantages().strip());
        company.setDisadvantages(request.getDisadvantages().strip());
        company.setZipCode(request.getZipCode());
        company.setDistanceCost(request.getDistanceCost());
        company.setWeightCost(request.getWeightCost());
        company.setVolumeCost(request.getVolumeCost());
        company.setTaxNumber(request.getTaxNumber());
        Company savedCompany = companyRepository.save(company);

        request.getTracks().forEach(trackRequest -> {
            Track track = new Track();
            track.setCompany(savedCompany);
            track.setFrom(DatabaseService.get(regionRepository::findById, trackRequest.from(), Region.class));
            track.setTo(DatabaseService.get(regionRepository::findById, trackRequest.to(), Region.class));
            track.setCost(trackRequest.cost());
            trackRepository.save(track);
        });

        request.getWorkHours().forEach(workHourRequest -> {
            WorkHour workHour = new WorkHour();
            workHour.setCompany(savedCompany);
            workHour.setOpen(workHourRequest.open());
            workHour.setClose(workHourRequest.close());
            workHour.setWeekDay(workHourRequest.weekDay());
            workHourRepository.save(workHour);
        });

        Optional<String> optionalLogo = imageService.saveImage(logoFile, PathConstants.COMPANY_LOGO_DIRECTORY);
        Optional<String> optionalLicence = imageService.saveImage(licenceFile, PathConstants.COMPANY_LICENCE_DIRECTORY);
        List<String> pictures = imageService.saveAllImages(picturesFiles, PathConstants.COMPANY_PICTURES_DIRECTORY);

        optionalLogo.ifPresent(savedCompany::setLogo);
        optionalLicence.ifPresent(savedCompany::setTradeLicence);
        savedCompany.getPictures().addAll(pictures);

        CompanyResponse response = companyMapper.map(companyRepository.save(savedCompany));
        return new ApiResponse(true, "Successful Registration.", response);
    }

    private User getSavedUser(String email, String password, String firstName, String lastName, String deviceToken) {
        DatabaseService.unique(userRepository::existLocalEmail, email, User.class);

        User user = new User();
        user.setEmail(email.strip().toLowerCase());
        user.setPassword(passwordEncoder.encode(password));
        user.setName(new Name(firstName, lastName));
        user.setRegistration(ZonedDateTime.now(ZoneId.of("Z")));
        user.setEnabled(true);

        if (StringUtils.hasLength(deviceToken)) {
            user.getDeviceTokens().add(deviceToken.strip());
        }

        do {
            user.setUsername(usernameProvider.apply(email));
        } while (userRepository.existsById(user.getUsername()));

        return userRepository.save(user);
    }

    @Override
    public Object login(String username, String password, String deviceToken) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));

        if (authentication.getPrincipal() instanceof User user) {
            if (StringUtils.hasLength(deviceToken)) {
                user.getDeviceTokens().add(deviceToken.strip());
                userRepository.save(user);
            }

            String response = tokenService.generate(user);
            return new ApiResponse(true, "Successful Login.", response);
        }

        throw new AuthenticationServiceException("Unknown Authentication.");
    }

    @Override
    public void logout(String token) {
        tokenService.revoke(token);
    }
}