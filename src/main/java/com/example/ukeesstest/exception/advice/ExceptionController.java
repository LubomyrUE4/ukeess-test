package com.example.ukeesstest.exception.advice;

import com.example.ukeesstest.exception.DefaultException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(DefaultException.class)
    @ResponseBody
    public ResponseEntity<?> handleDefaultException(DefaultException e) {
        Map<String, String> resultJSON = new HashMap<>();
        resultJSON.put("error", e.getMessage());
        return new ResponseEntity<>(resultJSON, HttpStatus.FORBIDDEN);
    }
    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseBody
    public ResponseEntity<?> handleValidationErrors(MethodArgumentNotValidException e) throws DefaultException {
        Map<String, String> resultJSON = new HashMap<>();
        resultJSON.put("error", e.getFieldError().getDefaultMessage());
        return new ResponseEntity<>(resultJSON, HttpStatus.FORBIDDEN);
    }
}
