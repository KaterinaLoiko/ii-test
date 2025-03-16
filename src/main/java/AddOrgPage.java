import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class AddOrgPage {

    private final SelenideElement addButton = $x("//span[text()='Добавить организацию']");

    public void clickAdd() {
        addButton.click();
    }

    public SelenideElement getAddButton() {
        return addButton;
    }
}
