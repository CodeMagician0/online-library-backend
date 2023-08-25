package com.codemagician.onlinelibrary.handler;

import com.codemagician.onlinelibrary.enums.MsgEnum;
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

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(MsgEnum.SYSTEM_ERROR.toString());
    }
}
