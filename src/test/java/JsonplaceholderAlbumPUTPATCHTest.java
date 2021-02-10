import com.github.javafaker.Faker;
import io.restassured.path.json.JsonPath;
import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class JsonplaceholderAlbumPUTPATCHTest {

    private final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private final String ALBUMS = "albums";

    private static Faker faker;
    private String fakeTitle;
    private Integer fakeUserId;

    @BeforeAll
    public static void beforeAll() {
        faker = new Faker();
    }

    @BeforeEach
    public void beforeEach() {
        fakeTitle = faker.lorem().sentence();
        fakeUserId = faker.number().randomDigit();
    }

    @Test
    public void jsonplaceholderCreateAlbum() {

        JSONObject album = new JSONObject();
        album.put("title", fakeTitle);
        album.put("userId", fakeUserId);

        System.out.println(album);

        Response response = given()
                .contentType("application/json")
                .body(album.toString())
                .when()
                .post(BASE_URL + "/" + ALBUMS + "/")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .extract()
                .response();

        JsonPath json = response.jsonPath();
        assertEquals(fakeTitle, json.get("title"));
        assertEquals(fakeUserId, json.get("userId"));

    }

    @Test
    public void jsonplaceholderPATCHAlbum() {

        JSONObject album = new JSONObject();
        album.put("title", fakeTitle);

        Response response = given()
                .pathParam("albumId", 1)
                .contentType("application/json")
                .body(album.toString())
                .when()
                .patch(BASE_URL + "/" + ALBUMS + "/" + "{albumId}")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        JsonPath json = response.jsonPath();
        assertEquals(fakeTitle, json.get("title"));

    }

}
