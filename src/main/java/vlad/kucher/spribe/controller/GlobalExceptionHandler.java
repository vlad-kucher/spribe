package vlad.kucher.spribe.controller;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import vlad.kucher.spribe.util.exception.ErrorInfo;
import vlad.kucher.spribe.util.exception.OpponentNotFoundException;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Vlad on 07.11.2017.
 */
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@ResponseBody
public class GlobalExceptionHandler {

    @ResponseStatus(value = HttpStatus.NOT_FOUND)  // 404
    @ExceptionHandler(OpponentNotFoundException.class)
    public ErrorInfo notFound(HttpServletRequest req, OpponentNotFoundException e) {
        return new ErrorInfo(req.getRequestURL().toString(), e);
    }

    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)  // 422
    @ExceptionHandler({IllegalArgumentException.class, NullPointerException.class})
    public ErrorInfo illegal(HttpServletRequest req, IllegalArgumentException e) {
        return new ErrorInfo(req.getRequestURL().toString(), e);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 500
    @ExceptionHandler(Exception.class)
    public ErrorInfo handleError(HttpServletRequest req, Exception e) {
        return new ErrorInfo(req.getRequestURL().toString(), e);
    }
}
