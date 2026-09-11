package es.upm.miw.apaw.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

@Log4j2
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
//@Profile({"dev"})
public class LoggingFilter extends OncePerRequestFilter {

    private static final int CACHE_LIMIT = 64 * 1024;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        log.debug(">{} >>>>>>> {}", request.getMethod(), request.getRequestURI());
        Map<String, String> headerMap = Collections.list(request.getHeaderNames()).stream()
                .collect(Collectors.toMap(name -> name, request::getHeader));
        log.debug("     Headers:{}", headerMap);
        Map<String, String> parameterMap = Collections.list(request.getParameterNames()).stream()
                .collect(Collectors.toMap(name -> name, request::getParameter));
        log.debug("     Parameters:{}", parameterMap);
        ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(request, CACHE_LIMIT);
        ContentCachingResponseWrapper wrappedResponse = new ContentCachingResponseWrapper(response);
        try {
            filterChain.doFilter(wrappedRequest, wrappedResponse);
        } catch (Exception e) {
            log.error("Error during filter processing", e);
            throw e;
        }
        byte[] requestArray = wrappedRequest.getContentAsByteArray();
        String requestBody = new String(requestArray, wrappedRequest.getCharacterEncoding());
        log.debug("     Body (request): {}", requestBody);
        log.debug("     Body (response): {}", formatResponseBody(wrappedResponse));
        wrappedResponse.copyBodyToResponse();
    }

    private String formatResponseBody(ContentCachingResponseWrapper response) throws IOException {
        String contentType = response.getContentType();
        if (contentType == null) {
            return new String(response.getContentAsByteArray(), response.getCharacterEncoding());
        }
        String baseType = contentType.split(";")[0].trim().toLowerCase();
        String label = switch (baseType) {
            case "application/pdf" -> "PDF";
            case "text/html" -> "HTML";
            case "text/css" -> "CSS";
            default -> baseType.startsWith("image/") ? "Imagen " + baseType.substring(6).toUpperCase() : null;
        };
        return label != null
                ? "[" + label + " - " + response.getContentSize() + " bytes]"
                : new String(response.getContentAsByteArray(), response.getCharacterEncoding());
    }
}


