package com.example.ukeesstest.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DefaultException extends Exception {
    private String message;
}
