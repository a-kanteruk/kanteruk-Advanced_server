package net.dunice.newsFeed.services;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import net.dunice.newsFeed.responses.CustomSuccessResponse;
import org.apache.commons.io.FilenameUtils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FilesServiceImpl implements FilesService {
    @Value("${upload.path}")
    private String uploadPath = "/home/dunice/repository/newsFeed/temp/";
    @Value("${file.url}")
    private String fileUrl;
    private Path root = Paths.get("temp/").toAbsolutePath();
    public static Path uploading;

    @Override
    public CustomSuccessResponse<String> uploadFile(MultipartFile file) throws IOException {
        String newFileName = UUID.randomUUID() + "." + FilenameUtils.getExtension(file.getOriginalFilename());
        Path copyLocation = Paths.get(uploadPath + File.separator + newFileName);
        uploading = Path.of(fileUrl + newFileName);
        Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
        return CustomSuccessResponse.getSuccessResponse(fileUrl + newFileName);
    }

    @Override
    public UrlResource loadFile(String fileName) throws MalformedURLException {
        Path file = root.resolve(fileName);
        return new UrlResource(file.toUri());
    }
}
