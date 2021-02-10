import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLOutput;
import java.util.List;

import static io.restassured.RestAssured.given;

public class JsonplaceholderReadAllUsersTest {

    private final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private final String USERS = "users";

    @Test
    public void jsonplaceholderReadAllUsersUsingStream() {

        Response response = given()
                .when()
                .get(BASE_URL + "/" + USERS)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        JsonPath json = response.jsonPath();
        List<String> emails = json.getList("email");

        long polishEmails = emails.stream()
                .filter(s -> s.endsWith(".pl"))
                .count();

        System.out.println("polishEmails " + polishEmails);
        Assertions.assertTrue(polishEmails == 0);

    }

    @Test
    public void jsonplaceholderCheckForBizEmails() {
        Response response = given()
                .when()
                .get(BASE_URL + "/" + USERS)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        JsonPath json = response.jsonPath();
        List<String> emails = json.getList("email");

        long bizEmails = emails.stream()
                .filter(s -> s.endsWith(".biz"))
                .count();

        System.out.println("bizEmails " + bizEmails);
        Assertions.assertTrue(bizEmails >= 0);
    }

}
