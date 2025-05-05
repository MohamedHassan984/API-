import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.lessThan;

public class GetLabelTest extends TestBase{

    static String url=baseUrl;

    @Test
    @Severity(SeverityLevel.MINOR)
    @Description("Retrieve labels assigned to a board or card.")

    public void getLabel(){

        String labelId=CreateLabelTest.myLableId;

        Response response= RestAssured.given()
                .baseUri(url)
                .basePath("/labels")
                .queryParam("key",key)
                .queryParam("token",token)
                .header("Content-Type", "application/json")
                .when()
                .get("/" +labelId)
                .then()
                .statusCode(200).time(lessThan(3000L))
                .extract()
                .response();

        response.prettyPrint();
        String labelColor = response.path("color");

        Assert.assertEquals(labelColor, "yellow");

    }
}
