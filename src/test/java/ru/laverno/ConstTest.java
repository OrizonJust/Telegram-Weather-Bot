package ru.laverno;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName(value = "Тестирование класса Const.")
class ConstTest {

    @Test
    @DisplayName(value = "Проверка доступа к конструктору класса Const.")
    void mustThrowExceptionForPrivateConstructor() throws NoSuchMethodException {
        final var c = Const.class.getDeclaredConstructor();
        Assertions.assertThrows(IllegalAccessException.class, c::newInstance);
    }
}
