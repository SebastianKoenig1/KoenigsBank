package com.OTH.KoenigsBank.exception;/*package com.SECI.DeviceManagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class DeviceExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<DeviceErrorResponse> handleException(DeviceNotFoundException exc) {

        // create a DeviceErrorResponse

        DeviceErrorResponse error = new DeviceErrorResponse();

        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        // return ResponseEntity

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<DeviceErrorResponse> handleException(DeviceConflictException exc) {

        // create a DeviceErrorResponse

        DeviceErrorResponse error = new DeviceErrorResponse();

        error.setStatus(HttpStatus.CONFLICT.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        // return ResponseEntity

        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler
    public ResponseEntity<DeviceErrorResponse> handleException(DeviceBadRequestExeption exc) {

        // create a DeviceErrorResponse

        DeviceErrorResponse error = new DeviceErrorResponse();

        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        // return ResponseEntity

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
*/