import io.qameta.allure.*;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

@Epic("API тесты")
@Feature("Тестирование страницы логина")
public class ApiLoginTest {

    private final static String EMAIL = "saperew170@ahaks.com";
    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = "https://org.1-ofd.ru/api/cp-core/user";
    }

    @Step("Создание спецификации запроса")
    public static RequestSpecification postSpec(String basePath, String body) {
        if (basePath == null || body == null) {
            throw new IllegalArgumentException("basePath и body не могут быть null");
        }
        return new RequestSpecBuilder()
                .setBody(body)
                .setBasePath(basePath)
                .setContentType(JSON)
                .build();
    }

    @Test
    @DisplayName("Проверка логина")
    @Description("Этот тест проверяет, что возможен логин")
    @Severity(SeverityLevel.BLOCKER)
    public void testLogin() {
        String body = String.format("{\"login\":\"%s\",\"password\":\"%s\",\"rememberme\":true}", EMAIL, "8f2bc376");
        Response response = given()
                .spec(postSpec("/login", body))
                .post();
        response.then().statusCode(200);
    }

    @Test
    @DisplayName("Проверка восстановления пароля")
    @Description("Этот тест проверяет, что возможно восстановление пароля")
    @Severity(SeverityLevel.BLOCKER)
    public void testRestorePassword() {
        String body = String.format("{\"login\":\"%s\"}", EMAIL);
        Response response = given()
                .spec(postSpec("/password/restore", body))
                .post();
        response.then().statusCode(200);
    }
}
