package ru.laverno;

public class Const {

    private Const() throws IllegalAccessException {
        throw new IllegalAccessException("Доступ к конструктору класса Const запрещён!");
    }

    public static final String TEMPERATURE_MESSAGE = """
            %s: %s.
            Температура: %.2f °C, ощущается как: %.2f °C.
            Ветер: %.2f м/с
            """;

    public static final String LOCATION_MESSAGE = """
            Геопозиция установлена: %s.
            """;

    public static final String LOCAL_DATE_TIME = "yyyy-MM-dd HH:mm:ss";
}
