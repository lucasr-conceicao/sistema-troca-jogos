package br.com.jogos.handler;

import br.com.jogos.exception.NotFoundException;
import br.com.jogos.exception.TrocaNaoPermitidaException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Objects;

@RestControllerAdvice
public class ControllerHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<JsonHandler> handlerRecursoNaoEncontradoException(MethodArgumentNotValidException ex, HttpServletRequest httpServlet) {
        return montarRetorno(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(), httpServlet.getRequestURI(), Objects.requireNonNull(ex.getBindingResult().getFieldError()).getDefaultMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    private ResponseEntity<JsonHandler> handlerNotFoundException(NotFoundException ex, HttpServletRequest httpServlet) {
        return montarRetorno(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(), httpServlet.getRequestURI(), ex.getMessage());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    private ResponseEntity<JsonHandler> handlerDataIntegrityViolationException(DataIntegrityViolationException ex, HttpServletRequest httpServlet) {
        return montarRetorno(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(), httpServlet.getRequestURI(), ex.getMessage());
    }

    @ExceptionHandler(TrocaNaoPermitidaException.class)
    private ResponseEntity<JsonHandler> handlerTrocaNaoPermitidaException(TrocaNaoPermitidaException ex, HttpServletRequest httpServlet) {
        return montarRetorno(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(), httpServlet.getRequestURI(), ex.getMessage());
    }

    private ResponseEntity<JsonHandler> montarRetorno(HttpStatus httpStatus, Integer code, String path, String mensagem) {
        return new ResponseEntity<>(new JsonHandler(LocalDateTime.now(), code , httpStatus, path, mensagem), httpStatus);
    }
}
