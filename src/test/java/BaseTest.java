import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class BaseTest {

    @BeforeAll
    static void setUp() {
        Configuration.startMaximized = true;
    }

    @AfterEach
    void tearDown() {
        closeWebDriver();
    }
}

