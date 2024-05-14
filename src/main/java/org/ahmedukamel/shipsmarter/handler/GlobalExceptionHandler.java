package org.ahmedukamel.shipsmarter.handler;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import org.ahmedukamel.shipsmarter.dto.api.ApiResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.lang.NonNull;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.function.Function;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Function<Exception, ApiResponse> function = exception -> new ApiResponse(false, exception.getLocalizedMessage(), "");

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(@NonNull HttpMessageNotReadableException exception, @NonNull HttpHeaders headers, @NonNull HttpStatusCode status, @NonNull WebRequest request) {
        return ResponseEntity.status(status).body(new ApiResponse(false, exception.getMessage().split(":")[0], ""));
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(@NonNull MissingServletRequestParameterException exception, @NonNull HttpHeaders headers, @NonNull HttpStatusCode status, @NonNull WebRequest request) {
        return ResponseEntity.status(status).body(function.apply(exception));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(@NonNull MethodArgumentNotValidException exception, @NonNull HttpHeaders headers, @NonNull HttpStatusCode status, @NonNull WebRequest request) {
        List<String> data = exception.getFieldErrors().stream().map(i -> "%s: %s".formatted(i.getField(), i.getDefaultMessage())).toList();
        return ResponseEntity.status(status).body(new ApiResponse(false, "Invalid Request Body.", data));
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(@NonNull HttpRequestMethodNotSupportedException exception, @NonNull HttpHeaders headers, @NonNull HttpStatusCode status, @NonNull WebRequest request) {
        return ResponseEntity.status(status).body(function.apply(exception));
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestPart(@NonNull MissingServletRequestPartException exception, @NonNull HttpHeaders headers, @NonNull HttpStatusCode status, @NonNull WebRequest request) {
        return ResponseEntity.status(status).body(function.apply(exception));
    }

    @ExceptionHandler(value = RuntimeException.class)
    protected ResponseEntity<Object> handleRuntimeException(@NotNull RuntimeException exception) {
        return ResponseEntity.status(HttpServletResponse.SC_BAD_REQUEST).body(function.apply(exception));
    }
}