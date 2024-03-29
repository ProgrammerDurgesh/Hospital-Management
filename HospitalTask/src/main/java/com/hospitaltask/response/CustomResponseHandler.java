package com.hospitaltask.response;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CustomResponseHandler extends  Exception{

    private static final long serialVersionUID = 1L;

	@Contract("_, _, _ -> new")
    public static @NotNull ResponseEntity<Object> response(String message, HttpStatus httpStatus, Object object) {
        Map<String, Object> map = new HashMap<>();
        map.put("Message ", message);
        map.put("status", httpStatus);
        map.put("data", object);
        return new ResponseEntity<>(map, httpStatus);

    }

}
