import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.lessThan;

public class CreateBoardTest extends TestBase {

    public static String myBoardId;
    static String url = TestBase.baseUrl;
    static String key = TestBase.key;
    static String token = TestBase.token;

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Description("Create a new board which is the main container for lists and cards.")

    public void createBoard() {

        Response response = RestAssured.given()
                .baseUri(url)
                .basePath("/boards")
                .queryParam("key", key)
                .queryParam("token", token)
                .queryParam("name", "My_First_Board")
                .queryParam("defaultLists", "false")
                .header("Content-Type", "application/json")
                .when().post()
                .then().statusCode(200).time(lessThan(4000L))
                .extract().response();

        response.prettyPrint();

        myBoardId = response.path("id");

        String boardName = response.path("name");

        Assert.assertEquals(boardName, "My_First_Board");
    }
}
