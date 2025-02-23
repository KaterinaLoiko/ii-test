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
import static org.hamcrest.Matchers.containsString;

@Epic("API тесты")
@Feature("Тестирование страницы логина")
public class ApiLoginTest {

    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = "https://org.1-ofd.ru";
    }

    @Step("Создание спецификации запроса")
    public static RequestSpecification postSpec(String basePath, String body) {
        return new RequestSpecBuilder()
                .setBody(body)
                .setBasePath(basePath)
                .setContentType(JSON)
                .build();
    }

    @Test
    @DisplayName("Проверка доступности страницы логина")
    @Description("Этот тест проверяет, что страница логина доступна и возвращает статус 200")
    @Severity(SeverityLevel.BLOCKER)
    public void testLoginPageAvailability() {
        Response response = given()
                .when()
                .get("/registration/login-mail")
                .then()
                .statusCode(200)
                .extract()
                .response();

        // Проверка, что в теле ответа содержится определенная строка
        response.then().body(containsString("Вход"));
    }

    @Test
    @DisplayName("Проверка заголовка ответа")
    @Description("Этот тест проверяет, что заголовок ответа содержит правильный Content-Type")
    @Severity(SeverityLevel.NORMAL)
    public void testLoginPageHeader() {
        given()
                .when()
                .get("/registration/login-mail")
                .then()
                .statusCode(200)
                .header("Content-Type", containsString("text/html"));
    }

    @Test
    @DisplayName("Проверка логина")
    @Description("Этот тест проверяет, что возможен логин")
    @Severity(SeverityLevel.BLOCKER)
    public void testLogin() {
        String body = String.format("{\"login\":\"%s\",\"password\":\"%s\",\"rememberme\":true}", "saperew170@ahaks.com", "8f2bc376");
        Response response = given()
                .spec(postSpec("/api/cp-core/use/login", body))
                .post();
        response.then().statusCode(200);
    }
}
