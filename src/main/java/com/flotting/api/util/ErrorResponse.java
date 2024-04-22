package com.flotting.api.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.h2.api.ErrorCode;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResponse  {

    private int errorCode;

    private String message;

}
