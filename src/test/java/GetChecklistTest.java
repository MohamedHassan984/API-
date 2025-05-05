import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.lessThan;

public class GetChecklistTest extends TestBase {

    static String url = TestBase.baseUrl;
    static String key = TestBase.key;
    static String token = TestBase.token;

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Retrieve checklists assigned to a card.")

    public void GetCheckList()
    {

        String CheckListId = CreateCheckListTest.myCheckListId;

        Response response = RestAssured.given()
                .baseUri(url)
                .basePath("/checklists")
                .queryParam("key",key)
                .queryParam("token",token)
                .header("Content-Type", "application/json")
                .when()
                .get("/"+CheckListId)
                .then()
                .statusCode(200).time(lessThan(3000L))
                .extract().response();

        response.prettyPrint();

        String checkListName = response.path("name");

        Assert.assertEquals(checkListName, "MyFirstChecklist");

    }
}
