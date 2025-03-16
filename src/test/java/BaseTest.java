import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class BaseTest {

    @BeforeAll
    static void setUp() {
        Configuration.remote = "http://192.168.53.187:4444";
        Configuration.startMaximized = true;
    }

    @AfterEach
    void tearDownAll() {
        closeWebDriver();
    }
}

