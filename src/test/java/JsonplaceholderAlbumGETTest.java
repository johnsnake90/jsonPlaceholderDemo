import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class JsonplaceholderAlbumGETTest {

    private final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private final String ALBUMS = "albums";

    @Test
    public void jsonAlbumReadOneAlbumWithPathVariable() {

        Response response = given()
                .pathParam("albumId", 1)
                .when()
                .get(BASE_URL + "/" + ALBUMS + "/" + "{albumId}")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        JsonPath json = response.jsonPath();

        Assertions.assertEquals("quidem molestiae enim", json.get("title"));
        Assertions.assertEquals(Integer.valueOf("1"), json.get("userId"));

    }

}
