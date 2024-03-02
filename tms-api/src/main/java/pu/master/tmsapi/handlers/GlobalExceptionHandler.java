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
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import pu.master.tmsapi.exceptions.CommentNotFoundException;
import pu.master.tmsapi.exceptions.ProjectNotFoundException;
import pu.master.tmsapi.exceptions.TaskNotFoundException;
import pu.master.tmsapi.exceptions.UserNotFoundException;
import pu.master.tmsapi.exceptions.UsernameAlreadyExistsException;


@RestControllerAdvice
public class GlobalExceptionHandler
{

    private final static String CAUGHT_EXCEPTION_MESSAGE = "An exception has been caught";
    private static final String GLOBAL_EXCEPTION_MESSAGE = "Something went wrong...";

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public ResponseEntity<Map<String, List<String>>> handleInternalAuthenticationServiceException(
                    final InternalAuthenticationServiceException exception)
    {
        LOGGER.error(CAUGHT_EXCEPTION_MESSAGE, exception);

        final Map<String, List<String>> errorsMap = formatErrorsResponse(exception.getMessage());
        return new ResponseEntity<>(errorsMap, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public ResponseEntity<Map<String, List<String>>> handleUsernameAlreadyExistsException(
                    final UsernameAlreadyExistsException exception)
    {
        LOGGER.error(CAUGHT_EXCEPTION_MESSAGE, exception);

        final Map<String, List<String>> errorsMap = formatErrorsResponse(exception.getMessage());
        return new ResponseEntity<>(errorsMap, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(CommentNotFoundException.class)
    public ResponseEntity<Map<String, List<String>>> handleCommentNotFoundException(final CommentNotFoundException exception)
    {
        LOGGER.error(CAUGHT_EXCEPTION_MESSAGE, exception);

        final Map<String, List<String>> errorsMap = formatErrorsResponse(exception.getMessage());

        return new ResponseEntity<>(errorsMap, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProjectNotFoundException.class)
    public ResponseEntity<Map<String, List<String>>> handleProjectNotFoundException(final ProjectNotFoundException exception)
    {
        LOGGER.error(CAUGHT_EXCEPTION_MESSAGE, exception);

        final Map<String, List<String>> errorsMap = formatErrorsResponse(exception.getMessage());

        return new ResponseEntity<>(errorsMap, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<Map<String, List<String>>> handleTaskNotFoundException(final TaskNotFoundException exception)
    {
        LOGGER.error(CAUGHT_EXCEPTION_MESSAGE, exception);

        final Map<String, List<String>> errorsMap = formatErrorsResponse(exception.getMessage());

        return new ResponseEntity<>(errorsMap, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, List<String>>> handleUserNotFoundException(final UserNotFoundException exception)
    {
        LOGGER.error(CAUGHT_EXCEPTION_MESSAGE, exception);

        final Map<String, List<String>> errorsMap = formatErrorsResponse(exception.getMessage());

        return new ResponseEntity<>(errorsMap, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, List<String>>> handleException(final Exception exception)
    {
        LOGGER.error(CAUGHT_EXCEPTION_MESSAGE, exception);

        final Map<String, List<String>> errorsMap = formatErrorsResponse(GLOBAL_EXCEPTION_MESSAGE);

        return new ResponseEntity<>(errorsMap, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    private Map<String, List<String>> formatErrorsResponse(final String... errors)
    {
        return formatErrorsResponse(Arrays.stream(errors).collect(Collectors.toList()));
    }


    private Map<String, List<String>> formatErrorsResponse(final List<String> errors)
    {
        final Map<String, List<String>> errorResponse = new HashMap<>(4);
        errorResponse.put("Errors", errors);
        return errorResponse;
    }
}
