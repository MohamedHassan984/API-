import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.lessThan;

public class PutLabelTest extends TestBase{

    static String url=baseUrl;

    @Test
    @Severity(SeverityLevel.MINOR)
    @Description("Update a label's name or color.")

    public void putLabel(){

        String labelId=CreateLabelTest.myLableId;

        Response response= RestAssured.given()
                .baseUri(url)
               .basePath("/labels")
                .queryParam("key",key)
                .queryParam("token",token)
                .queryParam("name","Update Label")
                .queryParam("color","purple")
                .header("Content-Type", "application/json")
                .when()
                .put("/" +labelId)
                .then()
                .statusCode(200).time(lessThan(3000L))
                .extract()
                .response();
        response.prettyPrint();
        String labelName=response.path("name");
        String labelColor=response.path("color");
        Assert.assertEquals(labelName,"Update Label");
        Assert.assertEquals(labelColor,"purple");
    }
}
