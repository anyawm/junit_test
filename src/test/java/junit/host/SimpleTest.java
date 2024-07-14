package junit.host;

import org.junit.jupiter.api.*;

@DisplayName("3 пробных теста")

public class SimpleTest {

    @Test
    @Tags({
            @Tag("SMOKE"),
            @Tag("REGRESS"),
    })
    @DisplayName("Первый пробный тест")
    void NumberOneTest() {
        System.out.println("Hello");
    }

    @Test
    @Tag("SMOKE")
    @DisplayName("Второй пробный тест")
    void NumberTwoTest() {
        System.out.println("я второй");
    }

    @Disabled("номер задачи")
    @Test
    @DisplayName("Третий пробный тест")
    void NumberThreeTest() {
        throw new AssertionError("падает");
    }

}
