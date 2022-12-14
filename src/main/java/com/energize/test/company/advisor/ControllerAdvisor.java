package com.energize.test.company.advisor;

import com.energize.test.company.zexceptions.AlreadyExistsException;
import com.energize.test.company.zexceptions.EmployeeNotFoundException;
import com.energize.test.company.zexceptions.NotValidException;
import com.energize.test.company.zexceptions.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(value = {AlreadyExistsException.class})
    public ResponseEntity<Object> handleAlreadyExistsException(AlreadyExistsException e) {

        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ExceptionResponse exception = new ExceptionResponse(
                e.getMessage(),
                badRequest.value(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(exception, badRequest);
    }

    @ExceptionHandler(value = {NotValidException.class})
    public ResponseEntity<Object> handleNotValidException(NotValidException e) {

        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ExceptionResponse exception = new ExceptionResponse(
                e.getMessage(),
                badRequest.value(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(exception, badRequest);
    }

    @ExceptionHandler(value = {EmployeeNotFoundException.class})
    public ResponseEntity<Object> handleNotFoundException(EmployeeNotFoundException e) {

        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ExceptionResponse exception = new ExceptionResponse(
                e.getMessage(),
                badRequest.value(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(exception, badRequest);
    }
}
