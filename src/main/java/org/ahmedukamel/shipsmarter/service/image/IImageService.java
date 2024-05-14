package org.ahmedukamel.shipsmarter.service.image;

import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

public interface IImageService {
    Optional<String> saveImage(MultipartFile file, Path path);

    Optional<String> saveImage(MultipartFile file, Path path, String name);

    List<String> saveAllImages(MultipartFile[] files, Path path);
}