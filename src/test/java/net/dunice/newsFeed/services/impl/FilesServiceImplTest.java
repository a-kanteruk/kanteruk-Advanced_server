package net.dunice.newsFeed.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import net.dunice.newsFeed.responses.CustomSuccessResponse;
import net.dunice.newsFeed.services.FilesService;
import net.dunice.newsFeed.services.FilesServiceImpl;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.UrlResource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

@SpringBootTest
@MockitoSettings(strictness = Strictness.LENIENT)
@ExtendWith(MockitoExtension.class)
public class FilesServiceImplTest {
    private FilesService filesService;

    @BeforeEach
    void setUp() {
        filesService =  new FilesServiceImpl();
    }

    @Test
    void TestMethod_UploadFile() throws IOException {
        CustomSuccessResponse response = filesService.uploadFile(getNewFile());
        Assertions.assertNotNull(response.getData());
    }

    @Test
    void TestMethod_LoadFile_LoadFileFromTemp() throws IOException {
        String pathFile = "temp/1bb9bfb1-49f2-4869-bb27-3f7ceeca9a45.jpeg";
        UrlResource resource = filesService.loadFile(pathFile);
        Assertions.assertNotNull(resource.getFile());
    }

    private MultipartFile getNewFile() throws IOException {
        File file = new File("src/test/resources/test.txt");
        FileInputStream input = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile("file", file.getName(), "text/plain",
                                                                                        IOUtils.toByteArray(input));
        return multipartFile;
    }
}
