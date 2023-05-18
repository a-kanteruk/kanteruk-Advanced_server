package net.dunice.newsFeed.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

import net.dunice.newsFeed.responses.CustomSuccessResponse;

import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;

public interface FilesService {
    CustomSuccessResponse<String> uploadFile(MultipartFile file) throws IOException;
    UrlResource loadFile(String fileName) throws FileNotFoundException, MalformedURLException;
}
