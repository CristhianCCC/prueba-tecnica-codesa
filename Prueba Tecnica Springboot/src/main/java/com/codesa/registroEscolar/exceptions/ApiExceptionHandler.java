package com.codesa.registroEscolar.exceptions;
import com.codesa.registroEscolar.common.StandardizedApiExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardizedApiExceptionResponse> handleValidationException(MethodArgumentNotValidException ex) {
        // Extraemos todos los mensajes de error de los campos
        String errores = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining("; "));

        StandardizedApiExceptionResponse response = new StandardizedApiExceptionResponse(
                "1001",
                "Errores de validación: " + errores,
                "validacion",
                "Error de validación",
                "Validacion"
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(BusinessRuleException.class)
    public ResponseEntity<StandardizedApiExceptionResponse> handleBusinessRuleException(BusinessRuleException ex) {
        StandardizedApiExceptionResponse response = new StandardizedApiExceptionResponse(
                ex.getCode(),
                ex.getMessage(),
                "validacion",
                "Error de validacion",
                "Business"
        );
        return ResponseEntity.status(ex.getHttpStatus()).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> unknownServerError(Exception ex) {
        StandardizedApiExceptionResponse standardizedApiExceptionResponse = new StandardizedApiExceptionResponse("1024",
                "Error de acceso: " + ex.getMessage(), "/customer/code{id}", "input output error", "Tecnico");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(standardizedApiExceptionResponse);
    }
}
