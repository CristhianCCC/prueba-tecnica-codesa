package com.codesa.registroEscolar.exceptions;


import org.springframework.http.HttpStatus;


//excepciones para el negocio

public class BusinessRuleException extends Exception{

    //añadiendo los atributos que se consideren necesarios
    private long id;
    private String code;
    private HttpStatus httpStatus;


    //añadiendo los constructores que se consideren necesarios
    public BusinessRuleException(String code, HttpStatus httpStatus, long id, String message) {
        super(message);
        this.code = code;
        this.httpStatus = httpStatus;
        this.id = id;
    }

    public BusinessRuleException(String code, String message, HttpStatus httpStatus) {
        super(message);
        this.code = code;
        this.httpStatus = httpStatus;
    }
    public BusinessRuleException(String message, Throwable cause) {
        super(message, cause);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
