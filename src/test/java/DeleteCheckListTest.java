import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.lessThan;

public class DeleteCheckListTest extends TestBase{
    String url=baseUrl;

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Delete a checklist from a card.")

    public void deleteCheckList(){
        String checkListId=CreateCheckListTest.myCheckListId;

        Response response= RestAssured.given()
                .baseUri(url)
                .basePath("/checklists")
                .queryParam("key",key)
                .queryParam("token",token)
                .header("Content-Type", "application/json")
                .delete("/"+checkListId)
                .then()
                .statusCode(200)
                .time(lessThan(3000L))
                .extract()
                .response();
        response.prettyPrint();

    }
}
