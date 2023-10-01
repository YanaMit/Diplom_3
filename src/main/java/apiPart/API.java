package apiPart;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class API {

    public static final String USER_REG_ENDPOINT = "/api/auth/register";
    public static final String LOGIN_ENDPOINT = "/api/auth/login";
    public static final String USER_DATA_ENDPOINT = "/api/auth/user";

    public static String accessToken;

   // Create user
    public static Response createUser(User user) {
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(user)
                .when()
                .post(USER_REG_ENDPOINT);
        return response;
    }

    //User log in
    public static Response logInUser(User user) {
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(user)
                .when()
                .post(LOGIN_ENDPOINT);
        return response;
    }

    public static String getAccessToken(User user) {
        accessToken = logInUser(user).then().extract().path("accessToken");
        return accessToken;
    }


    // Delete user
    public static void deleteUser(String accessToken) {
        given()
                .header("Content-type", "application/json")
                .and()
                .when()
                .delete(USER_DATA_ENDPOINT + accessToken);
    }


}
