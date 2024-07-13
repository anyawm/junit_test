package junit.host;

import com.codeborne.selenide.CollectionCondition;
import io.qameta.allure.AllureId;
import junit.host.data.Language;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


public class WebTest {

    @BeforeEach
    void setup() {
        open("https://ifarfor.ru/");
            }

    @ValueSource(strings = {
            "ваза", "игрушка"
    })
    @ParameterizedTest(name = "Для поиского запроса {0} должен отдавать не пустой список карточек")
    @AllureId("1")
    @Tag("BLOCKER")
   void searchResultsShouldNotBeEmpty(String searchQuery) {
        $("#isinput3").setValue(searchQuery).pressEnter();
        $$("#isearch").shouldBe(CollectionCondition.sizeGreaterThan(0));
    }


    //  @CsvSource(value = {
    //        "ваза | Ваза",
    //        "игрушка | Елочная игрушка"
    // }, delimiter = '|') // по умолчанию запятая

    @CsvFileSource(resources = "/test_data/searchResultsShouldContainExpectedResult.csv")
    @ParameterizedTest(name = "Для поиского запроса {0} должен находить фильтр {1}")
    @AllureId("1")
    @Tag("BLOCKER")
    void searchResultsShouldContainExpectedResult(String searchQuery, String searchFilter) {
        $("#isinput3").setValue(searchQuery).pressEnter();
        $(".icat3").shouldHave(text(searchFilter));
    }

    @Test
    @AllureId("2")
    @Tag("BLOCKER")
    @DisplayName("При переключении на английскую версию должен меняться язык")
    void successfulSwitchEnglishTest() {
        $(".llink").click();
        $(byText("My account")).shouldBe(visible);
    }

}
