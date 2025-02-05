import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {

    @BeforeAll
    static void setUp() {
        // Настройка Selenide
        Configuration.browser = "chrome"; // Вы можете изменить браузер по умолчанию
        Configuration.baseUrl = "https://org.1-ofd.ru";
        Configuration.startMaximized = true;
        Configuration.headless = false; // Установите true, если хотите запускать в headless режиме

        // Дополнительные настройки, если необходимо
        // System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
    }

    @AfterAll
    static void tearDownAll() {
        Selenide.closeWebDriver();
    }
}

