import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.number.OrderingComparison.lessThan;

public class DeleteLabelTest extends TestBase {

    String url=baseUrl;

    @Test
    @Severity(SeverityLevel.MINOR)
    @Description("Delete a label from a board or card.")

    public void deleteLabel(){
        String labelId=CreateLabelTest.myLableId;

        Response response= RestAssured.given()
                .baseUri(url)
                .basePath("/labels")
                .queryParam("key",key)
                .queryParam("token",token)
                .header("Content-Type", "application/json")
                .delete("/" +labelId)
                .then()
                .statusCode(200)
                .time(lessThan(3000L))
                .extract()
                .response();
        response.prettyPrint();
    }
}
