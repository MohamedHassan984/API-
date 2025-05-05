import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.testng.annotations.Test;

import static org.hamcrest.number.OrderingComparison.lessThan;


public class DeleteCardTest extends TestBase{
    String url=baseUrl;

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Delete an existing card from a list.")

    public void deleteCard(){
        String cardId=CreateCardTest.myCardId;
        Response response= RestAssured.given()
                .baseUri(url)
                .basePath("/cards")
                .queryParam("key",key)
                .queryParam("token",token)
                .header("Content-Type", "application/json")
                .delete("/"+cardId)
                .then()
                .statusCode(200)
                .time(lessThan(3000L))
                .extract()
                .response();
        response.prettyPrint();

    }
}
