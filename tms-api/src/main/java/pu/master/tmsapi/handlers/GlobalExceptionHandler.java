package pu.master.tmsapi.handlers;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler
{

    private final static String CAUGHT_EXCEPTION_MESSAGE = "An exception has been caught";
    private static final String GLOBAL_EXCEPTION_MESSAGE = "Something went wrong...";

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, List<String>>> handleException(Exception exception)
    {
        LOGGER.error(CAUGHT_EXCEPTION_MESSAGE, exception);

        final Map<String, List<String>> errorsMap = formatErrorsResponse(GLOBAL_EXCEPTION_MESSAGE);

        return new ResponseEntity<>(errorsMap, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    private Map<String, List<String>> formatErrorsResponse(String... errors)
    {
        return formatErrorsResponse(Arrays.stream(errors).collect(Collectors.toList()));
    }


    private Map<String, List<String>> formatErrorsResponse(List<String> errors)
    {
        final Map<String, List<String>> errorResponse = new HashMap<>(4);
        errorResponse.put("Errors", errors);
        return errorResponse;
    }
}
