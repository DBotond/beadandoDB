package controllers;

import com.sun.media.sound.InvalidFormatException;
import employees.exceptions.CEOAlreadyExists;
import employees.exceptions.IdNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String jsonMappingException(Exception ex){
        return ex.getMessage();
    }

    @ExceptionHandler(InvalidFormatException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String noEnumValueException(Exception ex){
        return "Invalid enum value at:" + ex.getMessage();
    }

    @ExceptionHandler(IdNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String IdNotFound(IdNotFoundException e){
        return "This id is not found: "+e.getMessage();
    }

    @ExceptionHandler(CEOAlreadyExists.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.IM_USED)
    public String duplicatedceo(CEOAlreadyExists e){
        return e.getMessage();
    }
}
