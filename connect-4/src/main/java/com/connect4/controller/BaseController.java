package com.connect4.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.connect4.model.ErrorResponse;

@ControllerAdvice
public class BaseController {

	private static final Logger logger = LoggerFactory.getLogger(BaseController.class);

    /**
     * Handles all Exceptions not addressed by more specific
     * <code>@ExceptionHandler</code> methods. Creates a response with the
     * Exception detail in the response body as JSON and a HTTP status code of
     * 500, internal server error.
     * 
     * @param e An Exception instance.
     * @return A ResponseEntity containing a the Exception attributes in the
     *         response body and a HTTP status code 500.
     */
    
    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    ErrorResponse handleException(Throwable throwable) {
    	  logger.error("> handleException");
          logger.error("- Exception: ", throwable.getMessage());
          logger.error("- Exception: ", throwable);
          logger.error("< handleException");
        return new ErrorResponse(throwable.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    ErrorResponse handleException(UnsupportedOperationException exception) {
  	  logger.error("> handleException");
      logger.error("- Exception: ", exception.getMessage());
      logger.error("- Exception: ", exception);
      logger.error("< handleException");
        return new ErrorResponse(exception.getMessage());
    }

}