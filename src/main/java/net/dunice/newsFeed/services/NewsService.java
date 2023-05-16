package net.dunice.newsFeed.services;

import java.util.UUID;

import net.dunice.newsFeed.dto.NewsDto;
import net.dunice.newsFeed.responses.CreateNewsSuccessResponse;

public interface NewsService {
    CreateNewsSuccessResponse createNews(NewsDto newsDto, UUID userId);
}
