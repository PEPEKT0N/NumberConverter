package org.top.springpractice.simple;

import org.springframework.stereotype.Service;
import org.top.springpractice.converter.DigitConverter;
import org.top.springpractice.converter.InvalidValueException;
import org.top.springpractice.converter.LowerThanZero;

import java.util.List;

@Service
public class SimpleDigitConverter implements DigitConverter {
    private final List<String> numberSystems;

    public SimpleDigitConverter() {
        numberSystems = List.of("2", "8", "10", "16");
    }

    public static String baseConvert(String from, String to, String value) {
        // преобразуем в десятичную
        int decimalValue = Integer.parseInt(value, Integer.parseInt(from));
        // возвращаем число в целевой с/с
        return Integer.toString(decimalValue, Integer.parseInt(to));
    }

    @Override
    public List<String> supportedNumberSystem() {
        return numberSystems;
    }

    @Override
    public String convert(String from, String to, String value) {
        try {
            int decimalValue = Integer.parseInt(value, Integer.parseInt(from));
            if (decimalValue < 0) {
                throw new LowerThanZero(value);
            }
            if (!numberSystems.contains(from)) {
                throw new InvalidValueException(from);
            }
            if (!numberSystems.contains(to)) {
                throw new InvalidValueException(to);
            }
        }
        catch (InvalidValueException | LowerThanZero e) {
            return e.getMessage();
        }
        return baseConvert(from, to, value);
    }
}
