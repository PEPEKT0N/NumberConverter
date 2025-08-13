package org.top.springpractice.api;

public class ApiMessages {
    // StringMessage - строковое сообщение
    public record StringMessage(String message) {}

    // DataToConvertMessage - данные для конвертации значения между двумя с/с
    public record DataToConvertMessage(String from, String to, String value) {}

    // ConvertResultMessage - результат конвертации
    public record ConvertResultMessage(String result, DataToConvertMessage arg) {}

    public record ErrorMessage(String type, String message) {}
}
