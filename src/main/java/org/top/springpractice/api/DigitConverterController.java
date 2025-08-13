package org.top.springpractice.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.top.springpractice.converter.DigitConverter;
import org.top.springpractice.api.ApiMessages.*;
import org.top.springpractice.converter.InvalidValueException;
import org.top.springpractice.converter.UnsupportedNumberException;

import java.util.List;

@RestController
@RequestMapping("api/digits")
public class DigitConverterController {

    private final DigitConverter converter;

    public DigitConverterController(DigitConverter digitConverter) {
        this.converter = digitConverter;
    }

    public List<String> supportedNumberSystems() {
        return converter.supportedNumberSystem();
    }

    @PostMapping("convert")
    public ConvertResultMessage convert(@RequestBody DataToConvertMessage data) {
        if (data.from() == null || data.from().isEmpty()) {
            throw new EmptyRequestDataException("from");
        }
        if (data.to() == null || data.to().isEmpty()) {
            throw new EmptyRequestDataException("to");
        }
        if (data.value() == null) {
            throw new EmptyRequestDataException("value");
        }
        String result = converter.convert(data.from(), data.to(), data.value());
        return new ConvertResultMessage(result, data);
    }

    @ExceptionHandler(EmptyRequestDataException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleEmptyRequestDataException(EmptyRequestDataException ex) {
        return new ErrorMessage(ex.getClass().getSimpleName(), ex.getMessage());
    }

    @ExceptionHandler(InvalidValueException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleInvalidValueException(InvalidValueException ex) {
        return new ErrorMessage(ex.getClass().getSimpleName(), ex.getMessage());
    }

    @ExceptionHandler(UnsupportedNumberException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage handleUnsupportedNumberException(UnsupportedNumberException ex) {
        return new ErrorMessage(ex.getClass().getSimpleName(), ex.getMessage());
    }
}
