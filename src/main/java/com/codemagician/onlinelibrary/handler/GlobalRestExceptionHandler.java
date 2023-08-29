package com.codemagician.onlinelibrary.handler;

import com.codemagician.onlinelibrary.enums.MsgEnum;
import com.codemagician.onlinelibrary.exception.BusinessException;
import com.codemagician.onlinelibrary.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Siuyun Yip
 * @version 1.0
 * @date 2023/8/24 17:45
 */
@RestControllerAdvice
public class GlobalRestExceptionHandler {

    /**
     * Resource not found handler
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<String> notFoundExceptionHandler(NotFoundException e) {
        return ResponseEntity.notFound().build();
    }

    /**
     * Business exception handler
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = BusinessException.class)
    public ResponseEntity<String> businessExceptionHandler(BusinessException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    /**
     * System error handler
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(MsgEnum.SYSTEM_ERROR.toString());
    }
}
