import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.testng.annotations.Test;
import org.testng.Assert;
import static org.hamcrest.Matchers.lessThan;

public class CreateListTest extends TestBase {

    public static String myListId;
    static String url = TestBase.baseUrl;
    static String key = TestBase.key;
    static String token = TestBase.token;

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Description("Create a new list inside a board to group cards.")

    public void createList()
    {

        String boardId = CreateBoardTest.myBoardId;

        Response response = RestAssured.given()
                .baseUri(url)
                .basePath("/lists")
                .queryParam("key", key)
                .queryParam("token", token)
                .queryParam("idBoard",boardId)
                .queryParam("name", "First_list")
                .header("Content-Type", "application/json")
                .when()
                .post()
                .then().statusCode(200).time(lessThan(3000L))
                .extract()
                .response();

        response.prettyPrint();

        myListId = response.path("id");

        String listName = response.path("name");

        Assert.assertEquals(listName, "First_list");
    }
}
