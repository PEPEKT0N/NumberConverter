package org.top.springpractice.converter;

import org.springframework.stereotype.Service;

import java.util.List;

// DigitConverter - конвертер чисел
@Service
public interface DigitConverter {
    // supportedNumberSystem() - получение списка доступных с/с
    List<String> supportedNumberSystem();

    // convert конвертация значений
    String convert(String from, String to, String value);
}
