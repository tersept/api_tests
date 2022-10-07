package apitests;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class ReqresInTests {

    UsersCreat user = new UsersCreat("morpheus", "leader");
    RegSuc email = new RegSuc("eve.holt@reqres.in", "pistol");


    @DisplayName("Тест на успешное создание пользователя")
    @Test
    void createTest() {
        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .body(
                        "name", is(user.getName()),
                        "job", is(user.getJob()),
                        "id", notNullValue(),
                        "createdAt", notNullValue()
                );
    }

    @DisplayName("Тест на не успешное создание пользователя при пустом body")
    @Test
    void createEmptyBodyTest() {
        given()
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(415);
    }

    @DisplayName("Тест на не успешное удаление пользователя")
    @Test
    void deleteTest() {
        given()
                .when()
                .delete("https://reqres.in/api/users/2")
                .then()
                .statusCode(204);
    }

    @DisplayName("Тест на не успешноую регистрацию")
    @Test
    void registerSuccessfulTest() {

        given()
                .contentType(ContentType.JSON)
                .body(email)
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .statusCode(200)
                .body(
                        "id", notNullValue(),
                        "token", notNullValue()
                );
    }

    @Test
    void registerEmptyBodylTest() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .statusCode(400)
                .body(
                        "error", is("Missing email or username")
                );
    }
}

