import org.junit.jupiter.api.Test;
import org.apache.http.HttpStatus;

import static io.restassured.RestAssured.given;

public class JsonplaceholderAlbumDELETETest {

    private final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private final String ALBUMS = "albums";

    @Test
    public void jsonplacehlderDeleteAlbum() {

        given()
                .pathParam("albumId", 1)
                .when()
                .delete(BASE_URL + "/" + ALBUMS + "/" + "{albumId}")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

    }

}
