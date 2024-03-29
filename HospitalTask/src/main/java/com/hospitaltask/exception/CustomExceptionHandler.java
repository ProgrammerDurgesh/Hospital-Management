package com.hospitaltask.exception;

import java.sql.SQLIntegrityConstraintViolationException;

import javax.mail.AuthenticationFailedException;
import javax.mail.SendFailedException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.hospitaltask.response.CustomResponseHandler;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = ExpiredJwtException.class)
    @ResponseBody
    public ResponseEntity<?> tokenExpired() {
        return CustomResponseHandler.response("Null Pointer Exception", HttpStatus.INTERNAL_SERVER_ERROR, "Token invalid");
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    @ResponseBody
    public ResponseEntity<?> userNotFoundException() {
        return CustomResponseHandler.response("User Not Found", HttpStatus.NOT_FOUND,"User Not Found");
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


    @ExceptionHandler(value = SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<?> sqlException() {
        return CustomResponseHandler.response("Record Already Exist", HttpStatus.INTERNAL_SERVER_ERROR, "SQL Error");
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    public ResponseEntity<?> accessDeniedException() {
        return CustomResponseHandler.response("Login first", HttpStatus.UNAUTHORIZED, "401");
    }
    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> methodArgumentTypeMismatchException() {
        return CustomResponseHandler.response("Enter Valid Data", HttpStatus.BAD_REQUEST, "401");
    }

    @ExceptionHandler(value = org.springframework.security.web.firewall.RequestRejectedException.class)
    public ResponseEntity<?> requestRejectedException() {
        return CustomResponseHandler.response("Remove Extra Slas in URL(URL NOT VALID)", HttpStatus.INTERNAL_SERVER_ERROR, "500");
    }

    @ExceptionHandler(value = MissingPathVariableException.class)
    public ResponseEntity<?> missingPathVariableException() {
        return CustomResponseHandler.response("@PathVariable Incorrect", HttpStatus.INTERNAL_SERVER_ERROR, "500");
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<?> httpRequestMethodNotSupportedException() {
        return CustomResponseHandler.response("Request Method Not Allowed", HttpStatus.METHOD_NOT_ALLOWED, "405");
    }

    @ExceptionHandler(value = AuthenticationFailedException.class)
    public ResponseEntity<?> javaxMailAuthenticationFailedException() {
        return CustomResponseHandler.response("Email Password incorrect", HttpStatus.INTERNAL_SERVER_ERROR, "501");
    }
    @ExceptionHandler(value = SendFailedException.class)
    public ResponseEntity<?> SendFailedException()
    {
        return CustomResponseHandler.response("Invalid Email Aaadress",HttpStatus.NOT_FOUND,"");
    }
}
