package com.lms.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.lms.util.ApiError;

@RestControllerAdvice
public class LibraryManagementExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiError> handleUserNotFoundException(UserNotFoundException exception){

        ApiError apiError = new ApiError();

        apiError.setMessage(exception.getMessage());
        apiError.setStatusCode(HttpStatus.NOT_FOUND.value());
        apiError.setTimeStamp(LocalDateTime.now());

        return new ResponseEntity<ApiError>(apiError,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AdminNotFoundException.class)
    public ResponseEntity<ApiError> handleAdminNotFoundException(AdminNotFoundException exception){

        ApiError apiError = new ApiError();

        apiError.setMessage(exception.getMessage());
        apiError.setStatusCode(HttpStatus.NOT_FOUND.value());
        apiError.setTimeStamp(LocalDateTime.now());

        return new ResponseEntity<ApiError>(apiError,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ApiError> handleBookNotFoundException(BookNotFoundException exception){

        ApiError apiError = new ApiError();

        apiError.setMessage(exception.getMessage());
        apiError.setStatusCode(HttpStatus.NOT_FOUND.value());
        apiError.setTimeStamp(LocalDateTime.now());

        return new ResponseEntity<ApiError>(apiError,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BorrowNotFoundException.class)
    public ResponseEntity<ApiError> handleBorrowNotFoundException(BorrowNotFoundException exception){

        ApiError apiError = new ApiError();

        apiError.setMessage(exception.getMessage());
        apiError.setStatusCode(HttpStatus.NOT_FOUND.value());
        apiError.setTimeStamp(LocalDateTime.now());

        return new ResponseEntity<ApiError>(apiError,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ReservationNotFoundException.class)
    public ResponseEntity<ApiError> handleReservationNotFoundException(ReservationNotFoundException exception){

        ApiError apiError = new ApiError();

        apiError.setMessage(exception.getMessage());
        apiError.setStatusCode(HttpStatus.NOT_FOUND.value());
        apiError.setTimeStamp(LocalDateTime.now());

        return new ResponseEntity<ApiError>(apiError,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ContactNotFoundException.class)
    public ResponseEntity<ApiError> handleContactNotFoundException(ContactNotFoundException exception){

        ApiError apiError = new ApiError();

        apiError.setMessage(exception.getMessage());
        apiError.setStatusCode(HttpStatus.NOT_FOUND.value());
        apiError.setTimeStamp(LocalDateTime.now());

        return new ResponseEntity<ApiError>(apiError,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BookAlreadyExistsException.class)
    public ResponseEntity<ApiError> handleBookAlreadyExistsException(BookAlreadyExistsException exception){

        ApiError apiError = new ApiError();

        apiError.setMessage(exception.getMessage());
        apiError.setStatusCode(HttpStatus.CONFLICT.value());
        apiError.setTimeStamp(LocalDateTime.now());

        return new ResponseEntity<ApiError>(apiError,HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ApiError> handleUserAlreadyExistsException(UserAlreadyExistsException exception){

        ApiError apiError = new ApiError();

        apiError.setMessage(exception.getMessage());
        apiError.setStatusCode(HttpStatus.CONFLICT.value());
        apiError.setTimeStamp(LocalDateTime.now());

        return new ResponseEntity<ApiError>(apiError,HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<ApiError> handleInvalidPasswordException(InvalidPasswordException exception){

        ApiError apiError = new ApiError();

        apiError.setMessage(exception.getMessage());
        apiError.setStatusCode(HttpStatus.UNAUTHORIZED.value());
        apiError.setTimeStamp(LocalDateTime.now());

        return new ResponseEntity<ApiError>(apiError,HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(BookNotAvailableException.class)
    public ResponseEntity<ApiError> handleBookNotAvailableException(BookNotAvailableException exception){

        ApiError apiError = new ApiError();

        apiError.setMessage(exception.getMessage());
        apiError.setStatusCode(HttpStatus.BAD_REQUEST.value());
        apiError.setTimeStamp(LocalDateTime.now());

        return new ResponseEntity<ApiError>(apiError,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BookAvailableException.class)
    public ResponseEntity<ApiError> handleBookAvailableException(BookAvailableException exception){

        ApiError apiError = new ApiError();

        apiError.setMessage(exception.getMessage());
        apiError.setStatusCode(HttpStatus.BAD_REQUEST.value());
        apiError.setTimeStamp(LocalDateTime.now());

        return new ResponseEntity<ApiError>(apiError,HttpStatus.BAD_REQUEST);
    }

}