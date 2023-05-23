package net.dunice.newsFeed.services.impl;

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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


@SpringBootTest
@MockitoSettings(strictness = Strictness.LENIENT)
@ExtendWith(MockitoExtension.class)
public class FilesServiceImplTest {
    FilesService filesService;

    @BeforeEach
    void setUp() {
        filesService =  new FilesServiceImpl();
    }

    @Test
    void TestMethod_uploadFile() throws IOException {

        CustomSuccessResponse response = filesService.uploadFile(getNewFile());
        Assertions.assertNotNull(response.getData());
    }

    @Test
    void TestMethod_loadFile_LoadFileFromTemp() throws IOException {
        String pathGeralt = "/home/dunice/repository/newsFeed/temp/e059c7ed-df4e-4717-90ee-3c5f54575995.jpg";
        UrlResource resource = filesService.loadFile(pathGeralt);

        Assertions.assertNotNull(resource.getFile());
    }

    private MultipartFile getNewFile() throws IOException {
        File file = new File("src/test/java/net/dunice/newsFeed/testResources/test.txt");
        FileInputStream input = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile("file", file.getName(), "text/plain",
                                                                                        IOUtils.toByteArray(input));
        return multipartFile;
    }

}
