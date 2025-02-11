package com.example.msauserapitest;

import com.example.msauserapitest.exception.BadRequestException;
import com.example.msauserapitest.exception.InternalServerErrorException;
import com.example.msauserapitest.exception.NotFoundException;
import com.example.msauserapitest.exception.UnauthorizedException;
import com.example.msauserapitest.exception.dto.ErrorResponse;
import com.example.msauserapitest.logging.MDCExceptionLogger;
import jakarta.validation.ConstraintViolationException;
import javassist.tools.reflect.CannotInvokeException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalRestControllerAdvice {
    private static final Logger logger = LoggerFactory.getLogger(GlobalRestControllerAdvice.class);

    // 500 Error, INTERNAL_SERVER_ERROR, Default (Not Custom Exception)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ErrorResponse handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        return errorResponse("올바르지 않은 값이 입력되었습니다.", "데이터 저장에 실패했습니다.", ex);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(CannotInvokeException.class)
    public ErrorResponse handleCannotInvokeException(CannotInvokeException ex) {
        return errorResponse("요청에 대한 응답이 없습니다.", ex.getMessage(), ex);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResponse handleException(Exception ex) {
        return errorResponse("예기치 않은 오류가 발생하였습니다.", "오류가 발생하였습니다.", ex);
    }

    //=================================================================================================================================================
    // 400 Error, BAD_REQUEST, Default (Not Custom Exception)

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResponse handleIllegalArgumentException(IllegalArgumentException ex) {
        return warnResponse("올바르지 않은 값이 입력되었습니다.", ex.getMessage(), ex);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ErrorResponse handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return warnResponse(
                "요청에 포함된 파라미터 혹은 데이터가 잘못되었습니다.",
                "요청에 포함된 파라미터 혹은 데이터가 잘못되었습니다." + ex.getMessage(),
                ex
        );
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalStateException.class)
    public ErrorResponse handleIllegalStateException(IllegalStateException ex) {
        return warnResponse(
                "올바르지 않은 상태값 입니다.",
                "요청에 포함된 파라미터 혹은 데이터가 잘못되었습니다." + ex.getMessage(),
                ex
        );
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ErrorResponse handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
        return warnResponse("요청에 포함된 파라미터 혹은 데이터가 잘못되었습니다.", ex);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return warnResponse(
                "요청에 포함된 파라미터 혹은 데이터가 잘못되었습니다.",
                ex.getBindingResult().getAllErrors().get(0).getDefaultMessage(),
                ex
        );
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ErrorResponse handleConstraintViolationException(ConstraintViolationException ex) {
        return warnResponse(
                "요청에 포함된 파라미터 혹은 데이터가 잘못되었습니다.",
                ex.getConstraintViolations().stream().toList().get(0).getMessage(),
                ex
        );
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ErrorResponse handleEmptyResultDataAccessException(EmptyResultDataAccessException ex) {
        return warnResponse("존재하지 않는 데이터에 접근하였습니다.", ex);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ErrorResponse handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        return warnResponse(
                "요청에 포함된 파라미터 혹은 데이터가 잘못되었습니다.",
                ex.getMessage(),
                ex
        );
    }

    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ErrorResponse handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        return warnResponse("잘못된 요청입니다.", ex);
    }

    //=================================================================================================================================================
    // Custom exception

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    public ErrorResponse handleBadRequestException(BadRequestException ex) {
        return warnResponse(
                ex.getErrorCode(),
                ex.getClientMessage(),
                ex
        );
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ErrorResponse handleNotFoundException(NotFoundException ex) {
        return warnResponse(
                ex.getErrorCode(),
                ex.getClientMessage(),
                ex
        );
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedException.class)
    public ErrorResponse handleUnauthorizedException(UnauthorizedException ex) {
        return warnResponse(
                ex.getErrorCode(),
                ex.getClientMessage(),
                ex
        );
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(InternalServerErrorException.class)
    public ErrorResponse handleInternalServerErrorException(InternalServerErrorException ex) {
        return warnResponse(
                ex.getErrorCode(),
                ex.getClientMessage(),
                ex
        );
    }

    //=================================================================================================================================================
    //log and response

    private ErrorResponse warnResponse(String message, Exception ex) {
        return warnResponse(message, message, ex);
    }

    private ErrorResponse warnResponse(String logMessage, String responseMessage, Exception ex) {
        logger.warn(logMessage, ex);
        MDCExceptionLogger.getStringStringMap(ex, "API_REQUEST_FAILED");

        return ErrorResponse.from(responseMessage);
    }


    private ErrorResponse errorResponse(String message, Exception ex) {
        return errorResponse(message, message, ex);
    }

    private ErrorResponse errorResponse(String logMessage, String responseMessage, Exception ex) {
        logger.error(logMessage, ex);
        MDCExceptionLogger.getStringStringMap(ex, "API_REQUEST_FAILED");

        return ErrorResponse.from(responseMessage);
    }
}