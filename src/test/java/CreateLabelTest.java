import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.lessThan;

public class CreateLabelTest extends TestBase{
    public static String myLableId;
    static String url = TestBase.baseUrl;

@Test
@Severity(SeverityLevel.MINOR)
@Description("Create a new label to categorize cards.")

    public void createLabel() {
    String boardId=CreateBoardTest.myBoardId;

    Response response = RestAssured.given()
            .baseUri(url)
            .basePath("/labels")
            .queryParam("key",key)
            .queryParam("token",token)
            .queryParam("name","label")
            .queryParam("color","yellow")
            .queryParam("idBoard",boardId)
            .header("Content-Type", "application/json")
            .when().post()
            .then()
            .statusCode(200).time(lessThan(3000L))
            .extract()
            .response();

    response.prettyPrint();
    myLableId=response.path("id");
    String labelName = response.path("name");
    String labelColor = response.path("color");

    Assert.assertEquals(labelName, "label");
    Assert.assertEquals(labelColor, "yellow");



}
}
