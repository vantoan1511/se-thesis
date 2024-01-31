package com.newswebsite.main.controller.advice;

import com.newswebsite.main.exception.ArticleNotFoundException;
import com.newswebsite.main.exception.InvalidArticleOperationException;
import com.newswebsite.main.exception.StateCodeNotFoundException;
import com.newswebsite.main.http.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {
    private boolean isAPIRequest(HttpServletRequest request) {
        return request.getRequestURI().contains("/api/");
    }

    private boolean isAdminRequest(HttpServletRequest request) {
        return request.getRequestURI().contains("/admin/");
    }

    @ExceptionHandler(InvalidArticleOperationException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public Object handleInvalidArticleOperationException(InvalidArticleOperationException ex,
                                                         HttpServletRequest request) {
        if (isAPIRequest(request)) {
            return ErrorResponse.builder()
                    .timestamp(new Date())
                    .statusCode(HttpStatus.FORBIDDEN.value())
                    .error(HttpStatus.FORBIDDEN.name())
                    .message(ex.getMessage())
                    .build();
        }
        String viewName = isAdminRequest(request) ? "admin/403" : "web/403";
        return new ModelAndView(viewName);
    }

    @ExceptionHandler({StateCodeNotFoundException.class, ArticleNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Object handleNotFoundException(RuntimeException ex,
                                          HttpServletRequest request) {
        if (isAPIRequest(request)) {
            return ErrorResponse.builder()
                    .timestamp(new Date())
                    .statusCode(HttpStatus.NOT_FOUND.value())
                    .error(HttpStatus.NOT_FOUND.name())
                    .message(ex.getMessage())
                    .build();
        }
        String viewName = isAdminRequest(request) ? "admin/404" : "web/404";
        return new ModelAndView(viewName);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex,
                                                                        HttpHeaders headers,
                                                                        HttpStatus status) {
        StringBuilder builder = new StringBuilder();
        ex.getBindingResult().getFieldErrors().forEach(item -> builder.append(item.getDefaultMessage()));
        ErrorResponse body = ErrorResponse.builder()
                .timestamp(Date.from(Instant.now()))
                .statusCode(status.value())
                .message(builder.toString())
                .build();
        return new ResponseEntity<>(body, headers, status);
    }
}
