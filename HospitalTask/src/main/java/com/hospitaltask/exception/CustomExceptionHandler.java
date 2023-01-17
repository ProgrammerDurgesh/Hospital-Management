package com.hospitaltask.exception;

import com.hospitaltask.response.CustomResponseHandler;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = ExpiredJwtException.class)
    @ResponseBody
    public ResponseEntity<?> tokenExpired() {
        return CustomResponseHandler.response("NULL Pointer Exception", HttpStatus.INTERNAL_SERVER_ERROR, "Token invalid");
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    @ResponseBody
    public ResponseEntity<?> userNotFoundException() {
        return CustomResponseHandler.response("NULL Pointer Exception", HttpStatus.NOT_FOUND, "Token invalid");
    }

    @ExceptionHandler(value = NullPointerException.class)
    @ResponseBody
    public ResponseEntity<?> nullPointerException() {
        return CustomResponseHandler.response("NULL Pointer Exception", HttpStatus.INTERNAL_SERVER_ERROR, "Token invalid");
    }


    @ExceptionHandler(value = HttpClientErrorException.Forbidden.class)
    @ResponseBody
    public ResponseEntity<Object> forbidden() {
        return CustomResponseHandler.response("NULL Pointer Exception", HttpStatus.INTERNAL_SERVER_ERROR, "Token invalid");
    }

    @ExceptionHandler(value = SignatureException.class)
    @ResponseBody
    public ResponseEntity<?> signatureException() {
        return CustomResponseHandler.response("Signature", HttpStatus.INTERNAL_SERVER_ERROR, "Token invalid");
    }

    @ExceptionHandler(value = HttpClientErrorException.NotFound.class)
    @ResponseBody
    public ResponseEntity<?> notFound() {
        return CustomResponseHandler.response("Signature", HttpStatus.INTERNAL_SERVER_ERROR, "Token invalid");
    }



}
