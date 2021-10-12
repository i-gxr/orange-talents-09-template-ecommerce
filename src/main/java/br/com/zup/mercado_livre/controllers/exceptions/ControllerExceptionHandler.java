package br.com.zup.mercado_livre.controllers.exceptions;

import br.com.zup.mercado_livre.controllers.dto.validations.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.*;
import org.springframework.context.i18n.*;
import org.springframework.http.*;
import org.springframework.validation.*;
import org.springframework.web.bind.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ValidationErrorsOutputDto handleValidationError(MethodArgumentNotValidException e) {
        List<ObjectError> globalErrors = e.getBindingResult().getGlobalErrors();
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        return buildValidationErrors(globalErrors, fieldErrors);
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(ProdutoForbiddenException.class)
    public Map<String, String> handleProdutoForbiddenError(BussinessException e) {
        Map<String, String> response = new HashMap<>();
        response.put("message:", e.getMessage());
        return response;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ProdutoNotFoundException.class)
    public Map<String, String> handleProdutoNotFoundError(BussinessException e) {
        Map<String, String> response = new HashMap<>();
        response.put("message:", e.getMessage());
        return response;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ImagemNotValidException.class)
    public Map<String, String> handleImagemNotValidError(BussinessException e) {
        Map<String, String> response = new HashMap<>();
        response.put("message:", e.getMessage());
        return response;
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(UsuarioNotFoundException.class)
    public Map<String, String> handleUsuarioNotFoundError(BussinessException e) {
        Map<String, String> response = new HashMap<>();
        response.put("message:", e.getMessage());
        return response;
    }

    private ValidationErrorsOutputDto buildValidationErrors(List<ObjectError> globalErrors, List<FieldError> fieldErrors) {
        ValidationErrorsOutputDto validationErrors = new ValidationErrorsOutputDto();
        globalErrors.forEach(error -> validationErrors.addError(getErrorMessage(error)));
        fieldErrors.forEach(error -> {
            String errorMessage = getErrorMessage(error);
            validationErrors.addFieldError(error.getField(), errorMessage);
        });
        return validationErrors;
    }

    private String getErrorMessage(ObjectError error) {
        return messageSource.getMessage(error, LocaleContextHolder.getLocale());
    }

}