package net.dunice.newsFeed.controller;

import javax.validation.Valid;

import lombok.RequiredArgsConstructor;
import net.dunice.newsFeed.dto.NewsDto;
import net.dunice.newsFeed.security.jwt.CustomUserDetails;
import net.dunice.newsFeed.services.NewsService;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/v1/news")
@RequiredArgsConstructor
public class NewsController {
    private final NewsService newsService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createNews(@Valid @RequestBody NewsDto newsDto,
                                     Authentication authentication) {
        CustomUserDetails cud = (CustomUserDetails) authentication.getPrincipal();
        return ResponseEntity.ok(newsService.createNews(newsDto, cud.getId()));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getNews(@RequestParam Integer page, @RequestParam Integer perPage) {
        return ResponseEntity.ok(newsService.getPaginatedNews(page, perPage));
    }
}
