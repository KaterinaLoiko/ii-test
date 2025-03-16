import com.codeborne.selenide.Condition;
import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("UI тесты")
@Feature("Тестирование страницы логина")
public class UiLoginTest extends BaseTest {

    private static final LoginPage loginPage = new LoginPage();
    private static final String LOGIN_EMAIL = "saperew170@ahaks.com";

    @BeforeEach
    void before() {
        loginPage.openPage();
    }

    @Test
    @DisplayName("Проверка видимости элементов на странице логина")
    @Description("Этот тест проверяет, что элементы на странице логина видимы")
    @Severity(SeverityLevel.CRITICAL)
    public void testLoginPageElementsVisibility() {
        loginPage.getEmail().shouldBe(visible);
        loginPage.getContinueButton().shouldBe(visible);
    }

    @Test
    @DisplayName("Проверка ввода корректного email и его отображения")
    @Description("Этот тест проверяет, что введенный email правильно отображается в поле ввода")
    @Severity(SeverityLevel.NORMAL)
    public void testCorrectEmailInputAndDisplay() {
        String email = "test@example.com";
        loginPage.setEmail(email);
        assertEquals(email, loginPage.getEmail().getValue());
    }

    @Test
    @DisplayName("Проверка входа с корректным email")
    @Description("Этот тест проверяет, что после ввода корректного email происходит редирект на страницу ввода пароля")
    @Severity(SeverityLevel.NORMAL)
    public void testLoginWithCorrectEmail() {
        loginPage.setEmail(LOGIN_EMAIL);
        loginPage.clickContinue();
        loginPage.password().shouldBe(visible);
        String currentUrl = url();
        assertTrue(currentUrl.contains("/registration/login-password"));
    }

    @Test
    @DisplayName("Проверка входа с некорректным email")
    @Description("Этот тест проверяет, что при вводе email несуществующего пользователя появляется сообщение об ошибке \"Пользователь не найден\"")
    @Severity(SeverityLevel.NORMAL)
    public void testLoginWithIncorrectEmail() {
        loginPage.setEmail("saperew170@ahaks.co");
        loginPage.clickContinue();
        loginPage.errorMessage().shouldBe(visible);
        assertEquals("Пользователь не найден", loginPage.errorMessage().getText());
    }

    @Test
    @DisplayName("Проверка входа с пустым email")
    @Description("Этот тест проверяет, что при попытке входа без ввода email цвет рамки у поля email стал красным.")
    @Severity(SeverityLevel.NORMAL)
    public void testLoginWithEmptyEmail() {
        loginPage.clickContinue();
        loginPage.getEmail().shouldBe(Condition.cssValue("border", "1px solid rgb(255, 66, 88)"));
    }

    @Test
    @DisplayName("Проверка успешного входа")
    @Description("Этот тест проверяет, что после успешного входа пользователь перенаправляется на страницу добавления организации")
    @Severity(SeverityLevel.CRITICAL)
    public void testSuccessfulLogin() {
        loginPage.setEmail(LOGIN_EMAIL);
        loginPage.clickContinue();
        loginPage.setPassword("8f2bc376");
        loginPage.clickContinue();
        new AddOrgPage().getAddOrganizationButton().shouldBe(visible);
        String currentUrl = url();
        assertTrue(currentUrl.contains("/user-account-confirmed"));
    }

    @Test
    @DisplayName("Проверка входа с неверным паролем")
    @Description("Этот тест проверяет, что при попытке входа с неверным паролем появляется ошибка.")
    @Severity(SeverityLevel.NORMAL)
    public void testLoginWithWrongPassword() {
        loginPage.setEmail(LOGIN_EMAIL);
        loginPage.clickContinue();
        loginPage.setPassword("ghdsjgd");
        loginPage.clickContinue();
        loginPage.errorMessage().shouldBe(visible);
        assertEquals("Неверный логин или пароль", loginPage.errorMessage().getText());
    }
}

