package net.dunice.newsFeed.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;
import net.dunice.newsFeed.models.LogEntity;
import net.dunice.newsFeed.repository.LogRepository;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class LoggerInterceptor implements HandlerInterceptor {
    private final LogRepository logRepository;

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler,
                                Exception ex) throws Exception {
        LogEntity log = new LogEntity().setMethod(request.getMethod())
                                        .setRemoteUser(request.getRemoteUser())
                                        .setUri(request.getRequestURI())
                                        .setResponseStatusCode(response.getStatus())
                                        .setContentType(response.getContentType());
        logRepository.save(log);
    }
}
