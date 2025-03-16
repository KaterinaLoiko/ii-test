import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import io.qameta.allure.*;

public class LoginPage {

    private final SelenideElement email = $("input[name='login']");
    private final SelenideElement password = $("input[type='password']");
    private final SelenideElement continueButton = $("app-button[text='Продолжить']");
    private final SelenideElement errorMessage = $("span[data-qa='snackbar-text']");

    public void openPage() {
        open("https://org.1-ofd.ru/registration/login-mail");
    }

    @Step("Установка электронной почты")
    public void setEmail(String email) {
        this.email.setValue(email);
    }

    @Step("Нажать Продолжить")
    public void clickContinue() {
        continueButton.click();
    }

    public SelenideElement getEmail() {
        return email;
    }

    public SelenideElement getContinueButton() {
        return continueButton;
    }

    public SelenideElement password() {
        return password;
    }

    @Step("Установка поля Пароль")
    public void setPassword(String password) {
        this.password.setValue(password);
    }

    public SelenideElement errorMessage() {
        return errorMessage;
    }
}
