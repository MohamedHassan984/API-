import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.lessThan;

public class CreateCheckListTest extends TestBase{


    public static String myCheckListId;
    static String url = TestBase.baseUrl;
    static String key = TestBase.key;
    static String token = TestBase.token;

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Add a checklist to a card for task management.")

    public void CreateCheckList()
    {
        String cardId = CreateCardTest.myCardId;

        Response response = RestAssured.given()
                .baseUri(url)
                .basePath("/checklists")
                .queryParam("key", key)
                .queryParam("token", token)
                .queryParam("idCard",cardId)
                .queryParam("name", "MyFirstChecklist")
                .header("Content-Type", "application/json")
                .when()
                .post()
                .then().statusCode(200).time(lessThan(3000L))
                .extract()
                .response();

        response.prettyPrint();

        myCheckListId = response.path("id");

        String checkListName = response.path("name");

        Assert.assertEquals(checkListName, "MyFirstChecklist");

    }
}
