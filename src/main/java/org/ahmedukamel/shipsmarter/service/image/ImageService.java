package org.ahmedukamel.shipsmarter.service.image;

import org.ahmedukamel.shipsmarter.constant.ImageConstants;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;

@Service
public class ImageService implements IImageService {

    @Override
    public Optional<String> saveImage(MultipartFile file, Path path) {
        try {
            isNotEmpty(file);
            isImage(file);

            if (Files.exists(path)) {
                Files.createDirectories(path);
            }

            Supplier<String> imageName = () -> "%s.%s".formatted(UUID.randomUUID(), FilenameUtils.getExtension(file.getOriginalFilename()));

            Path imagePath;
            do {
                imagePath = path.resolve(imageName.get());
            } while (Files.exists(imagePath));

            Files.copy(file.getInputStream(), imagePath);

            return Optional.of(imagePath.toString());
        } catch (Exception exception) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<String> saveImage(MultipartFile file, Path path, String name) {
        try {
            isNotEmpty(file);
            isImage(file);

            if (Files.exists(path)) {
                Files.createDirectories(path);
            }

            String imageName = "%s.%s".formatted(name, FilenameUtils.getExtension(file.getOriginalFilename()));

            Path imagePath;
            do {
                imagePath = path.resolve(imageName);
            } while (Files.exists(imagePath));

            Files.copy(file.getInputStream(), imagePath);

            return Optional.of(imagePath.toString());
        } catch (Exception exception) {
            return Optional.empty();
        }
    }

    @Override
    public List<String> saveAllImages(MultipartFile[] files, Path path) {
        return Arrays.stream(files)
                .map(file -> saveImage(file, path))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }

    private static void isNotEmpty(MultipartFile file) throws IOException {
        if (file.isEmpty())
            throw new IOException("No file selected.");
    }

    private static void isImage(MultipartFile file) throws IOException {
        if (ImageConstants.SUPPORTED_IMAGE_FORMATS.contains(file.getContentType())) return;
        throw new IOException("File isn't supported image format.");
    }
}