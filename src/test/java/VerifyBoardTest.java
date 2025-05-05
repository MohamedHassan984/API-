import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.lessThan;

public class VerifyBoardTest extends TestBase{

    static String url = TestBase.baseUrl;
    static String key = TestBase.key;
    static String token = TestBase.token;

    @Test
    @Severity(SeverityLevel.MINOR)
    @Description("Verify the data and structure of the created board.")

    public void CreateBoard() {

        String boardId = CreateBoardTest.myBoardId;

        Response response = RestAssured.given()
                .baseUri(url)
                .basePath("/boards")
                .queryParam("key", key)
                .queryParam("token", token)
                .header("Content-Type", "application/json")
                .when()
                .get("/"+boardId)
                .then().statusCode(200).time(lessThan(3000L))
                .extract()
                .response();

        response.prettyPrint();
    }
}
