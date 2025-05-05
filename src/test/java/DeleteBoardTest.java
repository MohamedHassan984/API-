import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.testng.annotations.Test;

import static org.hamcrest.number.OrderingComparison.lessThan;

public class DeleteBoardTest extends TestBase {
    String url = baseUrl;

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Delete an existing board along with its contents.")

    public void deleteCard() {
        String boardId = CreateBoardTest.myBoardId;
        Response response = RestAssured.given()
                .baseUri(url)
                .basePath("/boards")
                .queryParam("key", key)
                .queryParam("token", token)
                .header("Content-Type", "application/json")
                .delete("/" + boardId)
                .then()
                .statusCode(200)
                .time(lessThan(3000L))
                .extract()
                .response();
        response.prettyPrint();

    }
}