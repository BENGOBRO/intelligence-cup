package ru.bengobro.advice;

import com.vk.api.sdk.exceptions.*;
import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.bengobro.model.ErrorMessage;
import ru.bengobro.dto.TypeOfError;

@RestControllerAdvice
@Log4j
public class ServiceControllerAdvice {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorMessage> notReadableException(HttpMessageNotReadableException e) {
        log.error(e);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorMessage(TypeOfError.HTTP_MESSAGE_NOT_READABLE.getMessage()));
    }

    @ExceptionHandler(ApiAuthException.class)
    public ResponseEntity<ErrorMessage> authException(ApiAuthException e) {
        log.error(e);
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new ErrorMessage(TypeOfError.API_AUTH.getMessage()));
    }

    @ExceptionHandler(ApiAccessException.class)
    public ResponseEntity<ErrorMessage> accessException(ApiAccessException e) {
        log.error(e);
        return ResponseEntity
                .status(HttpStatus.NOT_ACCEPTABLE)
                .body(new ErrorMessage(TypeOfError.API_ACCESS.getMessage()));
    }

    @ExceptionHandler(ApiAccessGroupException.class)
    public ResponseEntity<ErrorMessage> accessGroupException(ApiAccessGroupException e) {
        log.error(e);
        return ResponseEntity
                .status(HttpStatus.NOT_ACCEPTABLE)
                .body(new ErrorMessage(TypeOfError.API_GROUP_ACCESS.getMessage()));
    }

    @ExceptionHandler(ApiException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorMessage> apiException(ApiException e) {
        log.error(e);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorMessage(TypeOfError.API.getMessage()));
    }

    @ExceptionHandler(ClientException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorMessage> clientException(ClientException e) {
        log.error(e);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorMessage(TypeOfError.CLIENT.getMessage()));
    }
}
