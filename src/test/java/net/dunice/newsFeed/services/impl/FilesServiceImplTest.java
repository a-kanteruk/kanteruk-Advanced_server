package net.dunice.newsFeed.services.impl;

import net.dunice.newsFeed.repository.UserRepository;
import net.dunice.newsFeed.responses.CustomSuccessResponse;
import net.dunice.newsFeed.security.jwt.JwtTokenProvider;
import net.dunice.newsFeed.services.FilesService;
import net.dunice.newsFeed.services.FilesServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.UrlResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

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
    void uploadFile(){

    }

    @Test
    void loadFile(){

    }

}
