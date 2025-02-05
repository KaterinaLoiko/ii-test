import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;

@Epic("UI тесты")
@Feature("Тестирование страницы логина")
public class UiLoginTest extends BaseTest {

    private static LoginPage loginPage = new LoginPage();

    @Test
    @DisplayName("Проверка видимости элементов на странице логина")
    @Description("Этот тест проверяет, что элементы на странице логина видимы")
    @Severity(SeverityLevel.CRITICAL)
    public void testLoginPageElementsVisibility() {
        loginPage.openPage();

        // Проверка видимости поля ввода email
        loginPage.getEmailInput().shouldBe(visible);

        // Проверка видимости кнопки входа
        loginPage.getLoginButton().shouldBe(visible);
    }

    @Test
    @DisplayName("Проверка ввода данных и отправки формы")
    @Description("Этот тест проверяет ввод данных в форму и отправку")
    @Severity(SeverityLevel.NORMAL)
    public void testLoginFormSubmission() {
        loginPage.openPage();

        // Ввод email
        loginPage.enterEmail("example@example.com");

        // Нажатие кнопки входа
        loginPage.clickLoginButton();

        // Проверка, что произошел редирект или появилось сообщение об ошибке
        // Например: $("div.error-message").shouldBe(visible);
    }
}

