package net.dunice.newsFeed.services;

import java.util.UUID;

import net.dunice.newsFeed.dto.NewsDto;
import net.dunice.newsFeed.responses.CreateNewsSuccessResponse;
import net.dunice.newsFeed.responses.CustomSuccessResponse;
import net.dunice.newsFeed.responses.PageableResponse;

public interface NewsService {
    CreateNewsSuccessResponse createNews(NewsDto newsDto, UUID userId);

    CustomSuccessResponse<PageableResponse> getPaginatedNews(int page, int perPage);

    CustomSuccessResponse<PageableResponse> getPaginatedUserNews(int page, int perPage, UUID userId);
}
