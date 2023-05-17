package net.dunice.newsFeed.services;

import net.dunice.newsFeed.responses.CustomSuccessResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public interface FilesService {
    CustomSuccessResponse<String> uploadFile(MultipartFile file) throws IOException;
}
