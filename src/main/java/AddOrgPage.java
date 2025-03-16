import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class AddOrgPage {

    private final SelenideElement addOrganizationButton = $x("//span[text()='Добавить организацию']");

    public void clickAddOrganisationButton() {
        addOrganizationButton.click();
    }

    public SelenideElement getAddOrganizationButton() {
        return addOrganizationButton;
    }
}
