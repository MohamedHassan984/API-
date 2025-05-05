import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.lessThan;

public class PutListTest extends TestBase {

    static String url = TestBase.baseUrl;
    static String key = TestBase.key;
    static String token = TestBase.token;

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Update the title of a list.")

    public void PutList ()
    {

        String listId = CreateListTest.myListId;

        Response response = RestAssured.given()
               .baseUri(url)
               .basePath("/lists")
               .queryParam("key", key)
               .queryParam("token", token)
               .queryParam("name", "new_list_name")
               .header("Content-Type", "application/json")
               .when()
               .put("/" +listId)
               .then().statusCode(200).time(lessThan(3000L))
               .extract()
               .response();

        response.prettyPrint();

        String listName = response.path("name");

        Assert.assertEquals(listName, "new_list_name");


    }
}
