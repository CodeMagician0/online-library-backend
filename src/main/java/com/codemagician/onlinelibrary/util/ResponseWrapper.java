package com.codemagician.onlinelibrary.util;

import com.codemagician.onlinelibrary.enums.MsgEnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Siuyun Yip
 * @version 1.0
 * @date 2023/9/12 18:28
 */
public class ResponseWrapper {

    public static ResponseEntity<Object> get(String message, HttpStatus status, Object responseObj) {
        Map<String, Object> map = new HashMap();
        map.put("message", message);
        map.put("status", status.value());
        map.put("data", responseObj);

        return new ResponseEntity(map, status);
    }
}
