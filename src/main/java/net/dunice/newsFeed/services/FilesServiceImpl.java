package net.dunice.newsFeed.services;

import net.dunice.newsFeed.responses.CustomSuccessResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class FilesServiceImpl implements FilesService{
    @Value("${upload.path}")
    private String uploadPath;
    @Override
    public CustomSuccessResponse<String> uploadFile(MultipartFile file) throws IOException {
        String randomUUID = UUID.randomUUID().toString();
        String resultFileName = randomUUID + "." + file.getOriginalFilename();
        file.transferTo(new File(resultFileName));
        String filePath = uploadPath + "/" + resultFileName;
        return CustomSuccessResponse.getSuccessResponse(filePath);
    }
}
