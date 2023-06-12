package com.taina.backendjava.Utils;

import org.springframework.http.ResponseEntity;

public class ResponseEntityUtils {

        public static <T> ResponseEntity<T> createResponseEntity(T value) {
            if (value != null) {
                return ResponseEntity.ok(value);
            } else {
                return ResponseEntity.notFound().build();
            }
        }

    }

