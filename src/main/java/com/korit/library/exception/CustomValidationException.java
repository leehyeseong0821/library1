package com.korit.library.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@AllArgsConstructor //전체 생성자
@Getter   //Map Getter
public class CustomValidationException extends RuntimeException {

    private Map<String,String> errorMap;
}
