import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.lessThan;

public class GeListTest extends TestBase {

    static String url = TestBase.baseUrl;
    static String key = TestBase.key;
    static String token = TestBase.token;

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Fetch list data from a board.")

    public void GetList()
    {

        String listId = CreateListTest.myListId;

        Response response = RestAssured.given()
                .baseUri(url)
                .basePath("/lists")
                .queryParam("key",key)
                .queryParam("token",token)
                .when()
                .get("/"+listId)
                .then()
                .statusCode(200).time(lessThan(3000L))
                .extract().response();

        response.prettyPrint();

        String listName = response.path("name");

        Assert.assertEquals(listName, "First_list");

    }
}
