import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.lessThan;

public class CreateCardTest  extends TestBase{

    public static String myCardId;
    static String url = TestBase.baseUrl;
    static String key = TestBase.key;
    static String token = TestBase.token;


    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Create a new card inside a list on a board.")

    public void createCard()
    {

        String listId = CreateListTest.myListId;

        Response response = RestAssured.given()
                .baseUri(url)
                .basePath("/cards")
                .queryParam("key", key)
                .queryParam("token", token)
                .queryParam("idList", listId)
                .queryParam("name", "MyFirstCard")
                .header("Content-Type", "application/json")
                .when()
                .post()
                .then().statusCode(200).time(lessThan(3000L))
                .extract()
                .response();

        response.prettyPrint();

        myCardId = response.path("id");

        String cardName = response.path("name");

        Assert.assertEquals(cardName, "MyFirstCard");
    }
}
