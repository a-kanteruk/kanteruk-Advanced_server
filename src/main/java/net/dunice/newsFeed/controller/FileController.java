package net.dunice.newsFeed.controller;

import lombok.RequiredArgsConstructor;
import net.dunice.newsFeed.services.FilesService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

@RestController
@RequestMapping("/v1/file")
@CrossOrigin
@RequiredArgsConstructor
public class FileController {
    private final FilesService filesService;

    @PostMapping(value = "/uploadFile",
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        return ResponseEntity.ok(filesService.uploadFile(file));
    }

    @GetMapping(value = "/{fileName}")
    public ResponseEntity getFile(@PathVariable String fileName) throws MalformedURLException, FileNotFoundException {
        Resource file = filesService.loadFile(fileName);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename/\"" + file.getFilename() + "\"")
                        .body(file);
    }
}
