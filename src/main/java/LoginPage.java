import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    private SelenideElement emailInput = $("input[name='email']");
    private SelenideElement loginButton = $("button[type='submit']");

    public void openPage() {
        Selenide.open("https://org.1-ofd.ru/registration/login-mail");
    }

    public void enterEmail(String email) {
        emailInput.setValue(email);
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public SelenideElement getEmailInput() {
        return emailInput;
    }

    public SelenideElement getLoginButton() {
        return loginButton;
    }
}
