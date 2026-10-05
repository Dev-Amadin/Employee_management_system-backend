package com.amadin.ems.exception;

import java.util.Date;

public record ExceptionResponse(int status, String error, String message, String path, Date timestamp) {

}
