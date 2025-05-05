import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.lessThan;

public class VerifyLabelTest  extends TestBase {
    static String url = baseUrl;

    @Test
    @Severity(SeverityLevel.TRIVIAL)
    @Description("Verify label creation and assignment.")

    public void getLabel() {
        String labelId = CreateLabelTest.myLableId;

        Response response = RestAssured.given()
                .baseUri(url)
                .basePath("/labels")
                .queryParam("key", key)
                .queryParam("token", token)
                .header("Content-Type", "application/json")
                .when()
                .get("/" + labelId)
                .then()
                .statusCode(200).time(lessThan(3000L))
                .extract()
                .response();

        response.prettyPrint();

    }
}
