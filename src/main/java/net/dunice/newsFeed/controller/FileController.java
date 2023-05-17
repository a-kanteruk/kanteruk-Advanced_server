package net.dunice.newsFeed.controller;

import lombok.RequiredArgsConstructor;
import net.dunice.newsFeed.services.FilesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/v1/file")
@RequiredArgsConstructor
public class FileController {
    private final FilesService filesService;

    @PostMapping("/uploadFile")
    public ResponseEntity uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        return ResponseEntity.ok(filesService.uploadFile(file));
    }
}
