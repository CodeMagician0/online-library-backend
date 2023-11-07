package com.codemagician.onlinelibrary.util;

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

    public static ResponseEntity<Object> build(String message, HttpStatus status, Object responseObj) {
        Map<String, Object> map = new HashMap();
        map.put("message", message);
        map.put("status", status.value());
        map.put("data", responseObj);

        return new ResponseEntity(map, status);
    }

    public static ResponseEntity success() {
        Map<String, Object> map = new HashMap();
        map.put("message", "success");
        map.put("status", 200);

        return new ResponseEntity(map, HttpStatus.OK);
    }
}
