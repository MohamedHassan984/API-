import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.lessThan;

public class VerifyCardTest extends TestBase{
    static String url = TestBase.baseUrl;
    static String key = TestBase.key;
    static String token = TestBase.token;

    @Test
    @Severity(SeverityLevel.MINOR)
    @Description("Verify the card properties and its existence.")

    public void GetCard()
    {
        String cardId = CreateCardTest.myCardId;

        Response response = RestAssured.given()
                .baseUri(url)
                .basePath("/cards")
                .queryParam("key",key)
                .queryParam("token",token)
                .header("Content-Type", "application/json")
                .when()
                .get("/"+cardId)
                .then()
                .statusCode(200).time(lessThan(3000L))
                .extract().response();

        response.prettyPrint();

        String cardName = response.path("name");

        Assert.assertEquals(cardName, "MyFirstCard");

    }
}
