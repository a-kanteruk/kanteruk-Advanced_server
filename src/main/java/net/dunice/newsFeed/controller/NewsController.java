package net.dunice.newsFeed.controller;

import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.RequiredArgsConstructor;
import net.dunice.newsFeed.constants.ValidationConstants;
import net.dunice.newsFeed.dto.NewsDto;
import net.dunice.newsFeed.security.jwt.CustomUserDetails;
import net.dunice.newsFeed.services.NewsService;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public ResponseEntity getNews(
            @RequestParam
            @NotNull(message = ValidationConstants.PARAM_PAGE_NOT_NULL)
            @Positive(message = ValidationConstants.PAGE_SIZE_NOT_VALID)
            @Max(message = ValidationConstants.REQUIRED_INT_PARAM_PAGE_IS_NOT_PRESENT, value = Integer.MAX_VALUE)
            @Min(message = ValidationConstants.REQUIRED_INT_PARAM_PAGE_IS_NOT_PRESENT, value = 1)
            Integer page,
            @RequestParam
            @NotNull(message = ValidationConstants.PARAM_PER_PAGE_NOT_NULL)
            @Positive(message = ValidationConstants.PAGE_SIZE_NOT_VALID)
            @Max(message = ValidationConstants.PER_PAGE_MAX_NOT_VALID, value = 1000)
            @Min(message = ValidationConstants.PER_PAGE_MIN_NOT_VALID, value = 1)
            Integer perPage) {
        return ResponseEntity.ok(newsService.getPaginatedNews(page, perPage));
    }

    @GetMapping(value = "/user/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getUserNews(
            @RequestParam
            @NotNull(message = ValidationConstants.PARAM_PAGE_NOT_NULL)
            @Positive(message = ValidationConstants.PAGE_SIZE_NOT_VALID)
            @Max(message = ValidationConstants.REQUIRED_INT_PARAM_PAGE_IS_NOT_PRESENT, value = Integer.MAX_VALUE)
            @Min(message = ValidationConstants.REQUIRED_INT_PARAM_PAGE_IS_NOT_PRESENT, value = 1)
            Integer page,
            @RequestParam
            @NotNull(message = ValidationConstants.PARAM_PER_PAGE_NOT_NULL)
            @Positive(message = ValidationConstants.PAGE_SIZE_NOT_VALID)
            @Max(message = ValidationConstants.PER_PAGE_MAX_NOT_VALID, value = 100)
            @Min(message = ValidationConstants.PER_PAGE_MIN_NOT_VALID, value = 1)
            Integer perPage,
            @PathVariable UUID userId) {
        return ResponseEntity.ok(newsService.getPaginatedUserNews(page, perPage, userId));
    }

    @GetMapping(value = "/find", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getFindNews(@RequestParam
                @NotNull(message = ValidationConstants.PARAM_PAGE_NOT_NULL)
                @Positive(message = ValidationConstants.PAGE_SIZE_NOT_VALID)
                @Max(message = ValidationConstants.REQUIRED_INT_PARAM_PAGE_IS_NOT_PRESENT, value = Integer.MAX_VALUE)
                @Min(message = ValidationConstants.REQUIRED_INT_PARAM_PAGE_IS_NOT_PRESENT, value = 1)
                Integer page,
                @NotNull(message = ValidationConstants.PARAM_PER_PAGE_NOT_NULL)
                @Positive(message = ValidationConstants.PAGE_SIZE_NOT_VALID)
                @Max(message = ValidationConstants.PER_PAGE_MAX_NOT_VALID, value = 100)
                @Min(message = ValidationConstants.PER_PAGE_MIN_NOT_VALID, value = 1)
                @RequestParam Integer perPage,
                @RequestParam(required = false) String author,
                @RequestParam(required = false) String keywords,
                @RequestParam(required = false) String[] tags) {
        return ResponseEntity.ok(newsService.getFindNews(page, perPage, author, keywords, tags));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity changeNews(@PathVariable Long id,
                                     @RequestBody NewsDto newsDto) {
        return ResponseEntity.ok(newsService.changeNews(id, newsDto));
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteNews(@PathVariable Long id) {
        return ResponseEntity.ok(newsService.deleteNews(id));
    }
}
